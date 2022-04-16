package com.ngai.auth.model.dto;

import lombok.Builder;

@Builder
public class ApiResponse {
    private String errorCode;
    private String message;
    private Object data;
}
