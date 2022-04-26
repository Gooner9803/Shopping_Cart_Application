package com.example.demo.mapper;

import com.example.demo.dto.UserDto;
import com.example.demo.persistance.user.User;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-04-26T12:57:38+0400",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 11.0.2 (Oracle Corporation)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public User mapToEntity(UserDto userDto) {
        if ( userDto == null ) {
            return null;
        }

        User user = new User();

        user.setId( userDto.getId() );
        user.setName( userDto.getName() );
        user.setSurname( userDto.getSurname() );
        user.setBirthDate( userDto.getBirthDate() );
        user.setRole( userDto.getRole() );
        user.setEmail( userDto.getEmail() );
        user.setPassword( userDto.getPassword() );

        user.setEnabled( true );

        return user;
    }

    @Override
    public UserDto mapToDto(User user) {
        if ( user == null ) {
            return null;
        }

        UserDto userDto = new UserDto();

        userDto.setId( user.getId() );
        userDto.setName( user.getName() );
        userDto.setSurname( user.getSurname() );
        userDto.setBirthDate( user.getBirthDate() );
        userDto.setRole( user.getRole() );
        userDto.setEmail( user.getEmail() );

        return userDto;
    }
}
