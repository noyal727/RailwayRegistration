package com.railways.registration.service.impl;

import com.railways.registration.client.ClientService;
import com.railways.registration.dto.LoginRequestDTO;
import com.railways.registration.dto.RegisterRequestDTO;
import com.railways.registration.entity.UserDetails;
import com.railways.registration.repository.UserRepository;
import com.railways.registration.service.RegisterService;
import com.railways.registration.utils.CustomHash;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
@Service
public class RegisterServiceIMPL implements RegisterService {
    @Autowired
    private UserRepository userRepository;

   @Autowired
   private ClientService clientService;
//    private LoginRepository loginRepository;

    @Override
    @Transactional
    public void registerUser(RegisterRequestDTO requestDTO) {
        UserDetails userDetails = new UserDetails();
        BeanUtils.copyProperties(requestDTO,userDetails);
        userRepository.save(userDetails);
        String sha256hex = CustomHash.hashString(requestDTO.getPassword());
        sha256hex = CustomHash.hashString(sha256hex);
        //String sha256hex = new BCryptPasswordEncoder().encode(requestDTO.getPassword());
        LoginRequestDTO loginRequestDTO = new LoginRequestDTO();
        loginRequestDTO.setUserName(requestDTO.getUserName());
        loginRequestDTO.setPassword(sha256hex);
        clientService.insertIntoLogin(loginRequestDTO);
        System.out.println(userDetails.toString());

    }
}
