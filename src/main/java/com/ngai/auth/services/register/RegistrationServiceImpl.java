package com.ngai.auth.services.register;

import com.ngai.auth.Utils.ErrorCodes;
import com.ngai.auth.Utils.Parameters;
import com.ngai.auth.Utils.Utility;
import com.ngai.auth.model.dto.LoginResponseDto;
import com.ngai.auth.model.dto.RegisterRequestDto;
import com.ngai.auth.model.dto.RegisterResponseDto;
import com.ngai.auth.model.entity.TUsers;
import com.ngai.auth.model.repository.TUsersRespository;
import com.ngai.auth.services.custom.ApiException;
import com.ngai.auth.services.custom.Messaging;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class RegistrationServiceImpl extends Messaging implements IRegistrationService{
    @Autowired
    TUsersRespository tUsersRespository;
    @Autowired
    BCryptPasswordEncoder passwordEncoder;

    @Override
    public RegisterResponseDto register(RegisterRequestDto registerRequestDto) {
        if(registerRequestDto == null) throw new ApiException(Parameters.INVALID_REQUEST, HttpStatus.BAD_REQUEST);

        if (Utility.isNullOrEmpty(registerRequestDto.getTel() ) || registerRequestDto.getTel().length() < 9)
            throw new ApiException(Parameters.INVALID_REQUEST, HttpStatus.BAD_REQUEST);

        if (Utility.isNullOrEmpty(registerRequestDto.getPwd())) throw new ApiException(Parameters.INVALID_REQUEST, HttpStatus.BAD_REQUEST);

        if (tUsersRespository.findByUsername(registerRequestDto.getUsername()).size() != 0)
            throw new ApiException(Parameters.USER_EXISTS, HttpStatus.CONFLICT);

        TUsers tUser = new TUsers(UUID.randomUUID().toString());
        tUser.setName(registerRequestDto.getName());
        tUser.setPassword(passwordEncoder.encode(registerRequestDto.getPwd()));
        tUser.setPhone(registerRequestDto.getTel());
        tUser.setStatus(Parameters.USER_STATUS.active.name()); //todo modify
        tUser.setUsername(registerRequestDto.getUsername());

        tUser = tUsersRespository.save(tUser);

        if (tUser == null) throw new ApiException(Parameters.REGISTRATION_FAILED, HttpStatus.INTERNAL_SERVER_ERROR);

        setMessage(Parameters.REGISTRATION_SUCCESSFUL);
        setErrCode(ErrorCodes.SUCCESS_CODE);
        return null;
    }

    @Override
    public LoginResponseDto refreshToken(String refreshToken) {
        return null;
    }


}
