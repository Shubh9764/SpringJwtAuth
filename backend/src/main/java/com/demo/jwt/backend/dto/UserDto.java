package com.demo.jwt.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class UserDto {

    private Long id;
    private String firstName;

    private String lastName;

    private String login;

    private String token;
}
