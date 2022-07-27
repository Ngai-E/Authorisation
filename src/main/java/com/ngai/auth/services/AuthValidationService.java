/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ngai.auth.services;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.google.common.io.BaseEncoding;
import com.ngai.auth.Utils.Parameters;
import com.ngai.auth.Utils.Utility;
import com.ngai.auth.components.ParamsCache;
import com.ngai.auth.model.entity.TClient;
import com.ngai.auth.model.repository.ITClientRepository;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.Optional;
import org.apache.commons.codec.digest.HmacUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 *
 * @author SOFT
 */
@Service
public class AuthValidationService {

    private static final Logger LOG = LogManager.getLogger(AuthValidationService.class);
    private final ITClientRepository clientRepository;
    private final BCryptPasswordEncoder passwordEncoder;


    private String userId;
    private String data;
    
    @Autowired
    public AuthValidationService(ITClientRepository clientRepository, BCryptPasswordEncoder passwordEncoder) {
        this.clientRepository = clientRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public boolean isValidSignature(String signedHeaderValue, String content) throws IOException {
        if (Utility.isNullOrEmpty(signedHeaderValue) || signedHeaderValue.split(Parameters.PARAM_AUTH_SEPARATOR).length != 2) {
            LOG.info("Content signature is {}", signedHeaderValue);
            return false;
        }

        String[] sigs = signedHeaderValue.split(Parameters.PARAM_AUTH_SEPARATOR);

        Optional<TClient> optClient = clientRepository.findByStrUsername(sigs[0]);

        if (optClient.isEmpty()) {
            LOG.info("client with username {} not a autorized application", sigs[0]);
            return false;
        }
        byte[] hmac = new HmacUtils(Parameters.PARAM_HMAC_ALGORITHM, optClient.get().getStrKey()).hmac(content);

        String computedHash = BaseEncoding.base64().encode(hmac);

        LOG.info("computed hash is {}", computedHash);
        return computedHash.equals(sigs[1]);
    }

    public String validateToken(String token) {
        String error = "";
        
        if (Utility.isNullOrEmpty(token)) return "Invalid token";
        String jwtKey = (String) ParamsCache.getParam(Parameters.PARAM_JWT_ENCRYPTION_KEY);

        DecodedJWT decodedJWT = null;
        try {
            decodedJWT = JWT.require(Algorithm.HMAC512(passwordEncoder.encode(jwtKey)))
                    .build()
                    .verify(token);

            System.out.println("expires at: " + decodedJWT.getExpiresAt());

            String userNameFromJwt = decodedJWT.getSubject();

            if (userNameFromJwt == null) return "Invalid user";

            this.userId = userNameFromJwt;
            this.data = decodedJWT.getPayload();

            return error;
        } catch (JWTVerificationException e) {
            LOG.error(e.getMessage());
            error = e.getLocalizedMessage();
        } catch (IllegalArgumentException e) {
           LOG.error(e.getMessage());
            error = e.getLocalizedMessage();
        }

        return error;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
