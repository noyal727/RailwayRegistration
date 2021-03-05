package com.railways.registration.service;


import com.railways.registration.dto.RegisterRequestDTO;
import com.railways.registration.dto.RegisterResponseDTO;
import org.springframework.http.ResponseEntity;

public interface RegisterService {
    ResponseEntity<RegisterResponseDTO> registerUser(RegisterRequestDTO requestDTO);
}
