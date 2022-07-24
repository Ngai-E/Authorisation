package com.ngai.auth.model.dto;

import lombok.Data;

@Data
public class RegisterRequestDto {
    private String name;
    private String username;
    private String pwd;
    private String tel;
    private String countryCode;
    private String referral;
}
