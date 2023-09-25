package com.demo.jwt.backend.mapper;


import com.demo.jwt.backend.dto.UserDto;
import com.demo.jwt.backend.pojo.User;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
@Component
public interface UserMapper {

    UserDto toUserDto(User user );
}
