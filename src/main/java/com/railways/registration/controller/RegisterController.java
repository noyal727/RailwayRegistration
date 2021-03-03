package com.railways.registration.controller;

import com.railways.registration.dto.RegisterRequestDTO;
import com.railways.registration.service.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/register")
public class RegisterController {
    @Autowired
    private RegisterService registerService;
    @PostMapping("")
    public void register(@RequestBody RegisterRequestDTO req){
        registerService.registerUser(req);
    }
}
