package com.ngai.auth.services.custom;

import org.springframework.http.HttpStatus;

public class ApiException extends RuntimeException{
    private HttpStatus statusCode;

    public ApiException(String message, HttpStatus statusCode) {
        super(message);
        this.statusCode = statusCode;
    }

    public HttpStatus getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(HttpStatus statusCode) {
        this.statusCode = statusCode;
    }
}
