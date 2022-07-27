package com.ngai.auth.services;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AuthValidationServiceTest {
    @Autowired
    AuthValidationService authValidationService;

    @Test
    public void testTokenVerification() {
        authValidationService.validateToken("ewrqe89qdfa5ao:afafiahfafnlafladf:fafdsfosdfhsdfos");

    }
}