package com.ngai.auth.model.dto;

import lombok.Data;

@Data
public class ContentSignatureValidation {
    private String contentToVerify;
    private String signatureToVerify;
}
