/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ngai.auth.controllers;

import com.ngai.auth.Utils.Utility;
import com.ngai.auth.services.AuthValidationService;
import java.io.IOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
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
    
    @PostMapping("/validate")
    public JSONObject validateAuthTokens(@RequestBody JSONObject validationObject) throws IOException {
        LOG.info(" Validating tokens for {}", validationObject);
        
        if (validationObject == null) return new JSONObject();
        
        JSONObject contentSig = validationObject.getJSONObject("contentSig");
        String token = validationObject.optString("token");
        
        if (contentSig != null) {
            boolean valid = authValidationService.isValidSignature(contentSig.optString("headerValue", ""), contentSig.optString("content", ""));
            validationObject.put("isSignatureValid", valid);
        }
        
        if (!Utility.isNullOrEmpty(token)) {
            String error = authValidationService.validateToken(token);
            validationObject.put("isTokenValid", error.isEmpty());
            validationObject.put("tokenMsg", error);
        }
        
        return validationObject;
    }
}
