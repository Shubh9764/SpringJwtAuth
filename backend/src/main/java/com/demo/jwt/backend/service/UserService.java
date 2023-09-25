package com.demo.jwt.backend.service;

import com.demo.jwt.backend.dto.CredentialsDto;
import com.demo.jwt.backend.dto.UserDto;
import com.demo.jwt.backend.mapper.UserMapper;
import com.demo.jwt.backend.pojo.User;
import com.demo.jwt.backend.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.nio.CharBuffer;

@Service

public class UserService {

    @Autowired
    UserRepo userRepo;

    @Autowired
    UserMapper userMapper;

    @Autowired
    PasswordEncoder encoder;
    public UserDto findByLogin(String login){
        User user =userRepo.findByLogin(login).orElseThrow(() -> new RuntimeException("no user found") );

        return userMapper.toUserDto(user);
    }

    public UserDto login(CredentialsDto dto){
        User user =userRepo.findByLogin(dto.getLogin()).orElseThrow(() -> new RuntimeException("no user found") );

        if (encoder.matches(CharBuffer.wrap(dto.getPassword()),user.getPassword())){
            return userMapper.toUserDto(user);
        }
        throw new RuntimeException("wrong password");

    }

    public UserDto register(User user){
        user.setPassword(encoder.encode(CharBuffer.wrap(user.getPassword())));
       return userMapper.toUserDto(userRepo.save(user));
    }

}
