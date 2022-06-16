package com.ngai.auth.services.user;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ngai.auth.Utils.ErrorCodes;
import com.ngai.auth.Utils.Parameters;
import com.ngai.auth.components.ParamsCache;
import com.ngai.auth.Utils.Utility;
import com.ngai.auth.model.dto.LoginRequestDto;
import com.ngai.auth.model.dto.LoginResponseDto;
import com.ngai.auth.model.dto.RegisterRequestDto;
import com.ngai.auth.model.dto.RegisterResponseDto;
import com.ngai.auth.model.entity.TToken;
import com.ngai.auth.model.entity.TUsers;
import com.ngai.auth.model.repository.TCountryCodesRepository;
import com.ngai.auth.model.repository.TTokenRepository;
import com.ngai.auth.model.repository.TUsersRespository;
import com.ngai.auth.security.User;
import com.ngai.auth.services.custom.ApiException;
import com.ngai.auth.services.custom.Messaging;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserServiceImpl extends Messaging implements IUserService {
    @Autowired
    TUsersRespository tUsersRespository;
    @Autowired
    TCountryCodesRepository tCountryCodesRepository;
    @Autowired
    BCryptPasswordEncoder passwordEncoder;
    @Autowired
    TTokenRepository tokenRepository;

    @Override
    public RegisterResponseDto register(RegisterRequestDto registerRequestDto) throws ApiException{
        if(registerRequestDto == null) throw new ApiException(Parameters.INVALID_REQUEST, HttpStatus.BAD_REQUEST);

        if (Utility.isNullOrEmpty(registerRequestDto.getTel() ) || registerRequestDto.getTel().length() < 9)//phone number should have country code
            throw new ApiException(Parameters.INVALID_REQUEST, HttpStatus.BAD_REQUEST);

        if (Utility.isNullOrEmpty(registerRequestDto.getPwd())) throw new ApiException(Parameters.INVALID_REQUEST, HttpStatus.BAD_REQUEST);

        if (!tUsersRespository.findByUsername(registerRequestDto.getUsername()).isEmpty())
            throw new ApiException(Parameters.USER_EXISTS, HttpStatus.CONFLICT);

        if (Utility.isNullOrEmpty(registerRequestDto.getCountryCode()) || tCountryCodesRepository.findById(registerRequestDto.getCountryCode()).isEmpty())
            throw new ApiException(Parameters.COUNTRY_N0T_SUPPORTED, HttpStatus.BAD_REQUEST);

        TUsers tUser = new TUsers(UUID.randomUUID().toString());
        tUser.setName(registerRequestDto.getName());
        tUser.setPassword(passwordEncoder.encode(registerRequestDto.getPwd()));
        tUser.setPhone(registerRequestDto.getCountryCode().concat(registerRequestDto.getTel()));
        tUser.setStatus(Parameters.USER_STATUS.active.name());
        tUser.setUsername(registerRequestDto.getUsername());
        tUser.setDtCreated(new Date());

        tUser = tUsersRespository.save(tUser);

        if (tUser == null) throw new ApiException(Parameters.REGISTRATION_FAILED, HttpStatus.INTERNAL_SERVER_ERROR);

        setMessage(Parameters.REGISTRATION_SUCCESSFUL);
        setErrCode(ErrorCodes.SUCCESS_CODE);
        return RegisterResponseDto
                .builder()
                .name(tUser.getName())
                .tel(tUser.getPhone())
                .username(tUser.getUsername())
                .build();
    }

    @Override
    public LoginResponseDto refreshToken(String refreshToken) {
        return null;
    }

    @Override
    public LoginResponseDto login(User user) {
        Date timeToExpire = new Date(System.currentTimeMillis() + Integer.parseInt((String) ParamsCache.getParam(Parameters.PARAM_JWT_TOKEN_EXPIRATION_TIME)));
        String jwtKey = (String) ParamsCache.getParam(Parameters.PARAM_JWT_ENCRYPTION_KEY);

        String token = JWT.create()
                .withSubject(user.getUsername())
                .withExpiresAt(timeToExpire)
                .sign(Algorithm.HMAC256(passwordEncoder.encode(jwtKey)));

        String refreshToken = UUID.randomUUID().toString();
        saveRefreshToken(user, refreshToken);

        LoginResponseDto loginResponseDto =LoginResponseDto.builder()
                .accessToken(token)
                .name(user.getName())
                .timeToExpire(Integer.parseInt((String) ParamsCache.getParam(Parameters.PARAM_JWT_TOKEN_EXPIRATION_TIME)) / 1000)
                .tel(user.getTel())
                .refreshToken(refreshToken)
                .username(user.getUsername())
                .build();

        setMessage(Parameters.SUCCESS);
        setErrCode(ErrorCodes.SUCCESS_CODE);
        return loginResponseDto;
    }

    private void saveRefreshToken(User user, String refreshToken) {
        TToken ttoken = new TToken(user.getUserId());
        ttoken.setDtCreated(new Date());
        ttoken.setLgTokenId(user.getUserId());
        ttoken.setStatus(Parameters.USER_STATUS.active.name());
        ttoken.setStrRefresh(refreshToken);

        tokenRepository.save(ttoken);
    }
}
