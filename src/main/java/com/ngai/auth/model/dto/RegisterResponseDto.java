package com.ngai.auth.model.dto;

import lombok.Data;

@Data
public class RegisterResponseDto {
    private String name;
    private String tel;
    private String isFreeTrial;
}
