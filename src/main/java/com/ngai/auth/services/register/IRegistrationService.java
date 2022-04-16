package com.ngai.auth.services.register;

import com.ngai.auth.model.dto.RegisterRequestDto;
import com.ngai.auth.model.dto.RegisterResponseDto;
import org.springframework.stereotype.Service;

public interface IRegistrationService {
    public RegisterResponseDto register(RegisterRequestDto registerRequestDto);
}
