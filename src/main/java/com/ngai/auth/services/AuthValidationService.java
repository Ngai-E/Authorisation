/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ngai.auth.services;

import com.google.common.io.BaseEncoding;
import com.ngai.auth.Utils.Parameters;
import com.ngai.auth.Utils.Utility;
import com.ngai.auth.model.entity.TClient;
import com.ngai.auth.model.repository.ITClientRepository;
import java.io.IOException;
import java.util.Optional;
import org.apache.commons.codec.digest.HmacUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author SOFT
 */
@Service
public class AuthValidationService {

    private static final Logger LOG = LogManager.getLogger(AuthValidationService.class);
    private final ITClientRepository clientRepository;

    @Autowired
    public AuthValidationService(ITClientRepository clientRepository) {
        this.clientRepository = clientRepository;
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
        
        return error;
    }
}
