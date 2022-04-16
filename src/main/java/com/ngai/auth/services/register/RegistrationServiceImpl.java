package com.ngai.auth.services.register;

import com.ngai.auth.model.dto.RegisterRequestDto;
import com.ngai.auth.model.dto.RegisterResponseDto;
import com.ngai.auth.services.custom.Messaging;
import org.springframework.stereotype.Service;

@Service
public class RegistrationServiceImpl extends Messaging implements IRegistrationService{
    @Override
    public RegisterResponseDto register(RegisterRequestDto registerRequestDto) {
        setMessage("failed");
        setErrCode("0001");
        return null;
    }


}
