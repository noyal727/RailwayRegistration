package com.railways.registration.dto;

import lombok.Data;

@Data
public class RegisterRequestDTO {
    private String userName;
    private String password;
    private String fname;
    private String mname;
    private String lname;
    private Long age;
    private String email;
    private Long phoneNumber;
}
