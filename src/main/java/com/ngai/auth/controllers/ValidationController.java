/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ngai.auth.controllers;

import com.ngai.auth.Utils.Utility;
import com.ngai.auth.model.dto.ContentSignatureValidation;
import com.ngai.auth.model.dto.SecurityValidationDto;
import com.ngai.auth.model.dto.Valid;
import com.ngai.auth.model.dto.ValidationResponse;
import com.ngai.auth.services.AuthValidationService;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author SOFT
 */
@RestController
@RequestMapping("/v1/validation")
public class ValidationController {
    private final static Logger LOG = LogManager.getLogger(ValidationController.class);
    
    @Autowired
    private AuthValidationService authValidationService;
    
    @PostMapping(value = "/validate", produces = MediaType.APPLICATION_JSON_VALUE)
    public ValidationResponse validateAuthTokens(@RequestBody SecurityValidationDto validationObject) throws IOException {
        LOG.info(" Validating tokens for {}", validationObject);

        ValidationResponse result = new ValidationResponse();
        if (validationObject == null) return result;

        ContentSignatureValidation contentSig = validationObject.getSignatureValidation();
        String token = validationObject.getTokenToVerify();
        
        if (contentSig != null) {
            boolean valid = authValidationService.isValidSignature(contentSig.getSignatureToVerify(), contentSig.getContentToVerify());

            result.setSignatureVerification(new Valid(valid, "", null, null));
        }
        
        if (!Utility.isNullOrEmpty(token)) {
            String error = authValidationService.validateToken(token);
            result.setTokenVerification(new Valid(Utility.isNullOrEmpty(error), error, authValidationService.getUserId(), authValidationService.getData()));
        }
        
        return result;
    }
}
