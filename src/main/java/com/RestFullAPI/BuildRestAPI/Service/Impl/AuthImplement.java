package com.RestFullAPI.BuildRestAPI.Service.Impl;

import com.RestFullAPI.BuildRestAPI.DTO.LoginReqestDTO;
import com.RestFullAPI.BuildRestAPI.DTO.LoginResponseDTO;
import com.RestFullAPI.BuildRestAPI.DTO.SignupRequestDTO;
import com.RestFullAPI.BuildRestAPI.DTO.SignupResponseDTO;
import com.RestFullAPI.BuildRestAPI.Entity.User;
import com.RestFullAPI.BuildRestAPI.Repository.UserRepository;
import com.RestFullAPI.BuildRestAPI.Security.AuthUtil;
import com.RestFullAPI.BuildRestAPI.Service.AuthService;
import org.jspecify.annotations.Nullable;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthImplement implements AuthService {

    private final AuthenticationManager authenticationManager;

    private final AuthUtil authUtil;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    public final PasswordEncoder passwordEncoder;

    public AuthImplement(AuthenticationManager authenticationManager, AuthUtil authUtil,
                         UserRepository userRepository , ModelMapper modelMapper, PasswordEncoder passwordEncoder) {
        this.authenticationManager = authenticationManager;
        this.authUtil = authUtil;
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public LoginResponseDTO login(LoginReqestDTO loginReqestDTO) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginReqestDTO.getUsername(), loginReqestDTO.getPassword())
        );
        //get Valid Authentication then i use
        User user = (User) authentication.getPrincipal();

        //How to create jwt Token with SecretKey => (Check in the AuthUtil Class)

        //generate Token
        String Token = authUtil.generateAccessToken(user);
        return new LoginResponseDTO(Token, user.getId());
    }

    @Override
    public SignupResponseDTO signup(SignupRequestDTO signReqestDTO) {
//        org.springframework.security.core.userdetails.User user = userRepository.findByUsername(signReqestDTO.getUsername()).orElseThrow(null);
//
//        if(user != null) throw new IllegalArgumentException("User Already Exist");
//
//        user = userRepository.save(user.builder()
//                .username(user.getUsername())
//                .password(user.getPassword())
//                .build()
//        );
//        return new SignupResponseDTO(user.getId(), user.getUsername());

        // 1. Check if user already exists
        if (userRepository.findByUsername(signReqestDTO.getUsername()).isPresent()) {
            throw new IllegalArgumentException("User already exists");
        }

        // 2. Map DTO → Entity
        User user = modelMapper.map(signReqestDTO, User.class);

        // 3. Encode password
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        // 4. Save user
        User savedUser = userRepository.save(user);

        // 5. Map Entity → Response DTO
        return modelMapper.map(savedUser, SignupResponseDTO.class);
    }
}
