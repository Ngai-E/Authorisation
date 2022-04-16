package com.ngai.auth.controllers;

import com.ngai.auth.Utils.Utility;
import com.ngai.auth.model.dto.ApiResponse;
import com.ngai.auth.model.dto.RegisterRequestDto;
import com.ngai.auth.services.register.RegistrationServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/user")
public class RegisterController {
    private static final Logger LOG = LogManager.getLogger(RegisterController.class);

    private RegistrationServiceImpl registrationService;

    @Autowired
    public  RegisterController(RegistrationServiceImpl registrationService){
        this.registrationService = registrationService;
    }

    @PostMapping("/register")
    public ResponseEntity<ApiResponse> register(@RequestBody RegisterRequestDto registerRequestDto){
        LOG.info("received registration request from: {}", registerRequestDto.getName());

        ApiResponse response = Utility.buildApiResponse(registrationService, registrationService.register(registerRequestDto));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
