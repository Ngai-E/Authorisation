package com.ngai.auth.model.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class LoginResponseDto {
    private String name;
    private String username;
    private String refreshToken;
    private String accessToken;
    private long timeToExpire;
    private String tel;

}
