package com.railways.registration.service.impl;

import com.railways.registration.client.ClientService;
import com.railways.registration.dto.LoginRequestDTO;
import com.railways.registration.dto.RegisterRequestDTO;
import com.railways.registration.dto.RegisterResponseDTO;
import com.railways.registration.entity.UserDetails;
import com.railways.registration.repository.UserRepository;
import com.railways.registration.service.RegisterService;
import com.railways.registration.utils.CustomHash;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class RegisterServiceIMPL implements RegisterService {
    @Autowired
    private UserRepository userRepository;

   @Autowired
   private ClientService clientService;
//    private LoginRepository loginRepository;

    @Override
    @Transactional
    public ResponseEntity<RegisterResponseDTO> registerUser(RegisterRequestDTO requestDTO) {
        RegisterResponseDTO response = new RegisterResponseDTO();
        if (requestDTO.getUserName() == null) {
            response.setBadRequest("Username Can't be null");
            return new ResponseEntity<>(response, HttpStatus.UNPROCESSABLE_ENTITY);
        } else if (requestDTO.getPassword() == null) {
            response.setBadRequest("Password Can't be null");
            return new ResponseEntity<>(response, HttpStatus.UNPROCESSABLE_ENTITY);
        } else if (requestDTO.getFname() == null) {
            response.setBadRequest("FirstName Can't be null");
            return new ResponseEntity<>(response, HttpStatus.UNPROCESSABLE_ENTITY);
        } else if (requestDTO.getAge() == null) {
            response.setBadRequest("Age Can't be null");
            return new ResponseEntity<>(response, HttpStatus.UNPROCESSABLE_ENTITY);
        } else if (requestDTO.getEmail() == null) {
            response.setBadRequest("Email Can't be null");
            return new ResponseEntity<>(response, HttpStatus.UNPROCESSABLE_ENTITY);
        } else if (requestDTO.getPhoneNumber() == null) {
            response.setBadRequest("PhoneNumber Can't be null");
            return new ResponseEntity<>(response, HttpStatus.UNPROCESSABLE_ENTITY);

        }
        else {
            Optional<UserDetails> optionalUserDetails = userRepository.findById(requestDTO.getUserName());
            if (optionalUserDetails.isPresent()) {
                response.setBadRequest("Username Exists");
                return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
            }
            UserDetails userDetails = new UserDetails();
            BeanUtils.copyProperties(requestDTO, userDetails);
            userRepository.save(userDetails);
            String sha256hex = CustomHash.hashString(requestDTO.getPassword());
            sha256hex = CustomHash.hashString(sha256hex);
            //String sha256hex = new BCryptPasswordEncoder().encode(requestDTO.getPassword());
            LoginRequestDTO loginRequestDTO = new LoginRequestDTO();
            loginRequestDTO.setUserName(requestDTO.getUserName());
            loginRequestDTO.setPassword(sha256hex);
            clientService.insertIntoLogin(loginRequestDTO);
            System.out.println(userDetails.toString());
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
    }
}
