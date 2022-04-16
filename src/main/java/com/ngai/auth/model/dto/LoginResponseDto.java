package com.ngai.auth.model.dto;

import lombok.Builder;

@Builder
public class LoginResponseDto {
    private String name;
    private String username;
    private String refreshToken;
    private String accessToken;
    private long timeToExpire;
    private String tel;

}
