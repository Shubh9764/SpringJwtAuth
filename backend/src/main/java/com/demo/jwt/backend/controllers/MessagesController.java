package com.demo.jwt.backend.controllers;

import com.demo.jwt.backend.dto.CredentialsDto;
import com.demo.jwt.backend.dto.UserDto;
import com.demo.jwt.backend.pojo.User;
import com.demo.jwt.backend.provider.UserAuthProvider;
import com.demo.jwt.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

@RestController
public class MessagesController {

    @Autowired
    UserService userService;

    @Autowired
    UserAuthProvider authProvider;

    @PostMapping("/login")
    public ResponseEntity<UserDto> login(@RequestBody CredentialsDto dto){
       UserDto user = userService.login(dto);
        user.setToken(authProvider.createToken(user.getLogin()));
        return ResponseEntity.ok(user);
    }

    @PostMapping("/register")
    public ResponseEntity<UserDto> login(@RequestBody User user){
        UserDto dto = userService.register(user);
        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }

    @GetMapping("/messages")
    public ResponseEntity<List<String>> messages(){
        return ResponseEntity.ok(Arrays.asList("first","second"));
    }
}
