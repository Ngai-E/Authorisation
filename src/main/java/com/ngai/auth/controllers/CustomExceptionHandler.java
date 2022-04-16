package com.ngai.auth.controllers;

import com.ngai.auth.Utils.Utility;
import com.ngai.auth.model.dto.ApiExceptionResponseDto;
import com.ngai.auth.services.custom.ApiException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(ApiException.class)
    public ResponseEntity<ApiExceptionResponseDto> handleApiException(ApiException apiExecption){
        return new ResponseEntity<>(Utility.buildExceptionResponse(apiExecption), apiExecption.getStatusCode());
    }
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiExceptionResponseDto> handleAllExceptions(Exception exception){
        return new ResponseEntity<>(Utility.buildAllExceptionResponse(exception), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
