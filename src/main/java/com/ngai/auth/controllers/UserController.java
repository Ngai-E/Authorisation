package com.ngai.auth.controllers;

import com.ngai.auth.Utils.Utility;
import com.ngai.auth.model.dto.ApiResponse;
import com.ngai.auth.model.dto.RegisterRequestDto;
import com.ngai.auth.security.User;
import com.ngai.auth.services.custom.ApiException;
import com.ngai.auth.services.user.UserServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/user")
public class UserController {
    private static final Logger LOG = LogManager.getLogger(UserController.class);

    private final UserServiceImpl registrationService;

    @Autowired
    public UserController(UserServiceImpl registrationService){
        this.registrationService = registrationService;
    }

    @PostMapping(value = "/register", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponse> register(@RequestBody RegisterRequestDto registerRequestDto) throws ApiException {
        LOG.info("received registration request from: {}", registerRequestDto.getName());

        ApiResponse response = Utility.buildApiResponse(registrationService, registrationService.register(registerRequestDto));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/refresh/{refreshToken}")
    public ResponseEntity<ApiResponse> refresh(@PathVariable(value = "refreshToken") String refreshToken) {
        LOG.info("received refresh token request: {}", refreshToken);

        ApiResponse response = Utility.buildApiResponse(registrationService, registrationService.refreshToken(refreshToken));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping(value = "/login", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponse> loginUser() {
        Authentication authResult = SecurityContextHolder.getContext().getAuthentication();
        LOG.info("getting token for user: {}", authResult.getName());

        User user = (User) authResult.getPrincipal();
        ApiResponse response = Utility.buildApiResponse(registrationService, registrationService.login(user));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
