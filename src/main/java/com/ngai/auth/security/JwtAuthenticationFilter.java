package com.ngai.auth.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ngai.auth.Utils.Parameters;
import com.ngai.auth.model.dto.LoginResponseDto;
import com.ngai.auth.services.custom.ApiException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

@Component
public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private AuthenticationManager authenticationManager;

    @Autowired
    public JwtAuthenticationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        try {
            User user = new ObjectMapper().readValue(request.getInputStream(), User.class);
            return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));

        } catch (BadCredentialsException ex) {
            throw new ApiException(Parameters.INVALID_CREDENTIALS, HttpStatus.UNAUTHORIZED);
        } catch (Exception ex) {
            throw new ApiException(Parameters.SERVER_ERROR, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        User user = (User) authResult.getPrincipal();
        Date timeToExpire = new Date(System.currentTimeMillis() + 1800000);
        String token = JWT.create()
                .withSubject(user.getUsername())
                .withExpiresAt(timeToExpire)
                .sign(Algorithm.HMAC256("mykey")); // todo put this in config

        LoginResponseDto loginResponseDto =LoginResponseDto.builder()
                .accessToken(token)
                .name(user.getName())
                .timeToExpire(timeToExpire.getTime())
                .tel(user.getTel())
                .refreshToken(null) //todo implement refresh tokens
                .username(user.getUsername())
                .build();

        response.getWriter().write(new ObjectMapper().writeValueAsString(loginResponseDto));
    }
}
