package com.ngai.auth.model.dto;

import lombok.Data;

@Data
public class LoginRequestDto {
    private String username;
    private String pwd;
}
