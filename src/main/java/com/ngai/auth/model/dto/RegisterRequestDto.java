package com.ngai.auth.model.dto;

import lombok.Data;

@Data
public class RegisterRequestDto {
    private String name;
    private String pwd;
    private String tel;
    private String refCode;
    private boolean isFreeTrial;
}
