package com.ngai.auth.model.dto;

import lombok.Data;

@Data
public class SecurityValidationDto {
    private ContentSignatureValidation signatureValidation;
    private String tokenToVerify;
}
