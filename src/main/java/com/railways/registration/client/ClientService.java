package com.railways.registration.client;

import com.railways.registration.dto.LoginRequestDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "login-client",url = "127.0.0.1:8085")
public interface ClientService {
    @RequestMapping(method = RequestMethod.POST,path = "/login/insert")
    void insertIntoLogin(@RequestBody LoginRequestDTO requestDTO);
}
