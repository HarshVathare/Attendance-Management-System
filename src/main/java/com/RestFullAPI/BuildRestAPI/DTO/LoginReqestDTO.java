package com.RestFullAPI.BuildRestAPI.DTO;

import lombok.*;

@Data
public class LoginReqestDTO {
    private String username;
    private String password;

    public LoginReqestDTO(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public LoginReqestDTO() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
