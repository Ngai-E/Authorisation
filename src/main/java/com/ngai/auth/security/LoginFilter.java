package com.ngai.auth.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.ngai.auth.Utils.Parameters;
import com.ngai.auth.components.ParamsCache;
import com.ngai.auth.Utils.Utility;
import static com.ngai.auth.Utils.Utility.objectMapper;
import com.ngai.auth.model.dto.ApiResponse;
import com.ngai.auth.model.dto.LoginResponseDto;
import com.ngai.auth.model.entity.TToken;
import com.ngai.auth.model.repository.TTokenRepository;
import com.ngai.auth.services.custom.ApiException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class LoginFilter extends UsernamePasswordAuthenticationFilter {
    private AuthenticationManager authenticationManager;

    public LoginFilter(AuthenticationManager authenticationManager) {
        this.setFilterProcessesUrl("/v1/user/login");
        this.setAuthenticationFailureHandler((request, response, failed) -> {
            this.unsuccessfulAuthentication(request, response, failed);
        });
        this.authenticationManager = authenticationManager;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        try {
            User user = objectMapper.readValue(request.getInputStream(), User.class);
            return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));

        } catch (UsernameNotFoundException ex) {
            throw new AuthException(Parameters.INVALID_USER, ex);
        }
        catch (BadCredentialsException ex) {
            throw new AuthException(Parameters.INVALID_CREDENTIALS, ex);
        } catch (Exception ex) {
            throw new AuthException(Parameters.SERVER_ERROR, ex);
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        SecurityContextHolder.getContext().setAuthentication(authResult);
        chain.doFilter(request, response);
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
        ApiResponse apiResponse = ApiResponse.builder()
                .errorCode(HttpStatus.UNAUTHORIZED.value() +"")
                .message(failed.getMessage())
                .build();

        response.getWriter().write(Utility.objectMapper.writeValueAsString(apiResponse));
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
    }

}
