package com.ekizmaz.user.controller;

import com.ekizmaz.common.client.contract.UserDto;
import com.ekizmaz.user.dto.AuthRequest;
import com.ekizmaz.user.dto.AuthResponse;
import com.ekizmaz.user.dto.UserSaveDto;
import com.ekizmaz.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class UserController {


    private final UserService userService;

    @GetMapping("/user/{id}")
    public ResponseEntity<UserDto> get(@PathVariable("id") Long id) {
        return ResponseEntity.ok(userService.get(id));
    }

    @PostMapping("/user")
    public ResponseEntity<UserDto> save(@RequestBody UserSaveDto user) {
        return ResponseEntity.ok(userService.save(user));
    }

    @PutMapping("/user")
    public ResponseEntity<UserDto> update(@PathVariable("id") Long id, @RequestBody UserDto account) {
        return ResponseEntity.ok(userService.update(id, account));
    }

    @DeleteMapping("/user")
    public void delete(Long id) {
        userService.delete(id);
    }

    @GetMapping("/users")
    public ResponseEntity<List<UserDto>> getAll(Pageable pageable) {
        return ResponseEntity.ok(userService.findAll(pageable));
    }

    @PostMapping(value = "/auth")
    public ResponseEntity<AuthResponse> getToken(@RequestBody AuthRequest request) {

        return new ResponseEntity<>(userService.getToken(request), HttpStatus.OK);

    }
}
