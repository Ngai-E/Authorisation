package com.ngai.auth.Utils;

import com.fasterxml.jackson.core.JsonParser.Feature;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.google.common.io.BaseEncoding;
import com.ngai.auth.model.dto.ApiExceptionResponseDto;
import com.ngai.auth.model.dto.ApiResponse;
import com.ngai.auth.model.entity.TClient;
import com.ngai.auth.model.repository.ITClientRepository;
import com.ngai.auth.services.custom.Messaging;
import java.io.IOException;
import java.util.Optional;
import org.apache.commons.codec.digest.HmacUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Utility {
    public static ObjectMapper objectMapper;

    static {
        objectMapper = new ObjectMapper();
        objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        objectMapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
        objectMapper.configure(Feature.AUTO_CLOSE_SOURCE, true);
    }

    public static ApiResponse buildApiResponse(Messaging messaging, Object data) {
        return ApiResponse.builder()
                .message(messaging.getMessage())
                .errorCode(messaging.getErrCode())
                .data(data)
                .build();
    }

    public static ApiExceptionResponseDto buildExceptionResponse(RuntimeException e) {
        return ApiExceptionResponseDto.builder()
                .message(e.getMessage())
                .build();
    }

    public static ApiExceptionResponseDto buildAllExceptionResponse(Exception e) {
        return ApiExceptionResponseDto.builder()
                .message(Parameters.SERVER_ERROR)
                .build();
    }

    public static boolean isNullOrEmpty(String data) {
        return data == null || data.isEmpty();
    }
  
    
}
