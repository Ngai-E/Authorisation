package com.ngai.auth.Utils;

import com.ngai.auth.model.dto.ApiExceptionResponseDto;
import com.ngai.auth.model.dto.ApiResponse;
import com.ngai.auth.services.custom.Messaging;

public class Utility {

    public static ApiResponse buildApiResponse(Messaging messaging, Object data) {
        return ApiResponse.builder()
                .message(messaging.getMessage())
                .errorCode(messaging.getErrCode())
                .data(data)
                .build();
    }

    public static ApiExceptionResponseDto buildExceptionResponse(RuntimeException e) {
        return ApiExceptionResponseDto.builder()
                .message(e.getMessage())
                .build();
    }

    public static ApiExceptionResponseDto buildAllExceptionResponse(Exception e) {
        return ApiExceptionResponseDto.builder()
                .message(Parameters.SERVER_ERROR)
                .build();
    }
}
