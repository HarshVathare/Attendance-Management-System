package com.RestFullAPI.BuildRestAPI.Service;

import com.RestFullAPI.BuildRestAPI.DTO.LoginReqestDTO;
import com.RestFullAPI.BuildRestAPI.DTO.LoginResponseDTO;
import com.RestFullAPI.BuildRestAPI.DTO.SignupRequestDTO;
import com.RestFullAPI.BuildRestAPI.DTO.SignupResponseDTO;
import org.jspecify.annotations.Nullable;
import org.springframework.stereotype.Service;

@Service
public interface AuthService {
    LoginResponseDTO login(LoginReqestDTO loginReqestDTO);

    SignupResponseDTO signup(SignupRequestDTO signupRequestDTO);
}
