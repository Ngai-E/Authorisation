package com.ngai.auth.security;

import com.google.common.io.BaseEncoding;
import com.ngai.auth.Utils.Parameters;
import com.ngai.auth.Utils.Utility;
import com.ngai.auth.model.dto.ApiResponse;
import com.ngai.auth.model.entity.TClient;
import com.ngai.auth.model.repository.ITClientRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;
import org.apache.commons.codec.digest.HmacUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.util.ContentCachingRequestWrapper;

public class BasicHeaderFilter extends BasicAuthenticationFilter {

    private static final Logger LOG = LogManager.getLogger(BasicHeaderFilter.class.getName());
    private final ITClientRepository clientRepository;
    
    public BasicHeaderFilter(AuthenticationManager authenticationManager, ITClientRepository clientRepository) {
        super(authenticationManager);
        this.clientRepository = clientRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println("came inside auth filter");  
        ContentCachingRequestWrapper req = new ContentCachingRequestWrapper(request);
        if (!isValidSignature(req)) {
            buildFaildResponse(response);
            return;
        }

        chain.doFilter(req, response);
    }
    
    private boolean isValidSignature(ContentCachingRequestWrapper request) throws IOException {
        String signature  = request.getHeader(Parameters.PARAM_AUTHORIZATION_HEADER);
        
        if (Utility.isNullOrEmpty(signature) || signature.split(Parameters.PARAM_AUTH_SEPARATOR).length != 2) {
            LOG.info("Content signature is {}", signature);
            return false;
        }
        
        String[] sigs = signature.split(Parameters.PARAM_AUTH_SEPARATOR);
        
        Optional<TClient> optClient = clientRepository.findByStrUsername(sigs[0]);
        
        if (optClient.isEmpty()) {
            LOG.info("client with username {} not a autorized application", sigs[0]);
            return false;
        }
        
        String contentToVerify = buildContentToHashFromRequest(request); //hash content with clients username and client key
        
        String computedHash = buildHashFromContent(contentToVerify, optClient.get().getStrKey());
        
        LOG.info("computed hash is {}", computedHash);
        return computedHash.equals(sigs[1]);
    }
    
    private void buildFaildResponse(HttpServletResponse response) throws IOException{
        ApiResponse apiResponse = ApiResponse.builder()
                .errorCode(HttpStatus.UNAUTHORIZED.value() +"")
                .message(Parameters.INVALID_CREDENTIALS)
                .build();

        response.getWriter().write(Utility.objectMapper.writeValueAsString(apiResponse));
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
    }
    
    private String buildHashFromContent(String data, String key) {   
        byte[] hmac =  new HmacUtils(Parameters.PARAM_HMAC_ALGORITHM, key).hmac(data);
        
        return BaseEncoding.base64().encode(hmac);
    }
    
    private String buildContentToHashFromRequest(ContentCachingRequestWrapper request) throws IOException {
        StringBuilder path = new StringBuilder(request.getRequestURI());
        String queryString = request.getQueryString();
        
        if (!Utility.isNullOrEmpty(queryString)) 
            path.append(Parameters.PARAM_SEPARATOR_QUESTION)
                    .append(queryString);
       
        String body = "";
//        String body = request.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
        
        return new StringBuilder()
                .append(path)
                .append(body)
                .toString();
    }
}
