package com.RestFullAPI.BuildRestAPI.DTO;

import org.springframework.stereotype.Component;

/************************************************************************
 Developer Name : Harshvardhan Vathare
 Developer Content : harsh1234vathare@gmail.com
 Created On : 12/26/2025 4:05 PM
 Project Name : BuildRestAPI
 ************************************************************************/
//@Component
public class StudentDTO {
    private long id;
    private String name;
    private String email;

    public StudentDTO(long id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    public StudentDTO() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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
