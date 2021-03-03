package com.railways.registration.service;


import com.railways.registration.dto.RegisterRequestDTO;

public interface RegisterService {
    void registerUser(RegisterRequestDTO requestDTO);
}
