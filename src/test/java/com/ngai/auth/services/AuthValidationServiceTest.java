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
        authValidationService.validateToken("eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIwOGU5NjQyYS1lNDU2LTQ4Y2MtYjhjMi03YjdlZWM1NDQxNTYiLCJpc3MiOiJhdXRoMCIsIm5hbWUiOiJoZWxlbiIsImV4cCI6MTY2MTcwMjEzMCwidXNlcm5hbWUiOiJFbGluZ2FpIn0.nve2PDCFvymzSOdJ_mtYKgBOjxMeVcF-yZ9rOzvA6K0");

    }
}