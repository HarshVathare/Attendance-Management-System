package com.RestFullAPI.BuildRestAPI.DTO;

import lombok.Data;

@Data
public class LoginResponseDTO {
    String jwt;
    Long userId;

    public LoginResponseDTO() {
    }

    public LoginResponseDTO(String jwt, Long userId) {
        this.jwt = jwt;
        this.userId = userId;
    }

    public String getJwt() {
        return jwt;
    }

    public void setJwt(String jwt) {
        this.jwt = jwt;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
