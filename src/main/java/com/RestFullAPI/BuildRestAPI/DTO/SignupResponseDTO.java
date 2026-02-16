package com.RestFullAPI.BuildRestAPI.DTO;

import lombok.*;

@Data
//@Getter
//@Setter
//@AllArgsConstructor
//@NoArgsConstructor
//@Builder
public class SignupResponseDTO {
    private long id;
    private String username;

    public SignupResponseDTO(Long id, String username) {
        this.id = id;
        this.username = username;
    }

    public SignupResponseDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
