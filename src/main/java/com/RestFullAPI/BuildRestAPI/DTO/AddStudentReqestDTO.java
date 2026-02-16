package com.RestFullAPI.BuildRestAPI.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.springframework.stereotype.Component;


//@Component
public class AddStudentReqestDTO {
    @NotBlank(message = "Email is required")
    @Size(min = 3, max = 30, message = "Name should be of length 3 to 30")
    private String name;

    @Email
    @NotBlank(message = "Email is required")
    private String email;

    public AddStudentReqestDTO( String name, String email) {
        this.name = name;
        this.email = email;
    }

    public AddStudentReqestDTO() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
