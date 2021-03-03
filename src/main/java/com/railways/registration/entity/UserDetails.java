package com.railways.registration.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
public class UserDetails {
    @Id
    private String userName;
    private String fname;
    private String mname;
    private String lname;
    private Long age;
    private String email;
    private Long phoneNumber;
}
