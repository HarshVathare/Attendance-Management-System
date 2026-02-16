package com.RestFullAPI.BuildRestAPI.Controller;

import com.RestFullAPI.BuildRestAPI.DTO.LoginReqestDTO;
import com.RestFullAPI.BuildRestAPI.DTO.LoginResponseDTO;
import com.RestFullAPI.BuildRestAPI.DTO.SignupRequestDTO;
import com.RestFullAPI.BuildRestAPI.DTO.SignupResponseDTO;
import com.RestFullAPI.BuildRestAPI.Service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    @CrossOrigin(origins = "http://127.0.0.1:5500/")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginReqestDTO loginReqestDTO){
        return ResponseEntity.ok(authService.login(loginReqestDTO));
    }

    @PostMapping("/signup")
    @CrossOrigin(origins = "http://127.0.0.1:5500/")
    public ResponseEntity<SignupResponseDTO> signup(@RequestBody SignupRequestDTO signupRequestDTO){
        return ResponseEntity.ok(authService.signup(signupRequestDTO));
    }

}
