package com.ngai.auth.services.user;

import com.ngai.auth.model.dto.LoginRequestDto;
import com.ngai.auth.model.dto.LoginResponseDto;
import com.ngai.auth.model.dto.RegisterRequestDto;
import com.ngai.auth.model.dto.RegisterResponseDto;
import com.ngai.auth.security.User;

public interface IUserService {
    public RegisterResponseDto register(RegisterRequestDto registerRequestDto) throws  Exception;
    public LoginResponseDto refreshToken(String refreshToken);
    public LoginResponseDto login(User user);
}
