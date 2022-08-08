package com.ekizmaz.payment.client;

import com.ekizmaz.payment.dto.UserDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(value = "userService", url = "http://localhost:8088")
public interface UserServiceClient {
    @RequestMapping("/user/{id}")
    ResponseEntity<UserDto> get(@PathVariable("id") Long id);
}

