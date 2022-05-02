package com.ngai.auth.model.dto;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;

@Builder
@Getter
public class ApiResponse {
    private String errorCode;
    private String message;
    private Object data;
}
