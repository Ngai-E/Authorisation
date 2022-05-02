package com.ngai.auth.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ngai.auth.Utils.ErrorCodes;
import com.ngai.auth.model.dto.ApiResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class AuthEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        PrintWriter printWriter = httpServletResponse.getWriter();
        ApiResponse body = ApiResponse.builder()
                .message("NOT AUTHORIZED")
                .errorCode(ErrorCodes.UNAUTHORIZED)
                .build();

        printWriter.write(new ObjectMapper().writeValueAsString(body));
    }
}
