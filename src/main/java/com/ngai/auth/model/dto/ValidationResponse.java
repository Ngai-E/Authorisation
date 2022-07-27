package com.ngai.auth.model.dto;

import lombok.Data;

@Data
public class ValidationResponse {
    private Valid tokenVerification;
    private Valid signatureVerification;
}
