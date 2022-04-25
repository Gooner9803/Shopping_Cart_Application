package com.example.demo.mapper;

import com.example.demo.dto.UserDto;
import com.example.demo.persistance.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "enabled", expression = "java(true)")
    User mapToEntity(UserDto userDto);

    @Mapping(target = "password", ignore = true)
    UserDto mapToDto(User user);
}
