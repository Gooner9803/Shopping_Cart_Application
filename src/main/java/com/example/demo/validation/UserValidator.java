package com.example.demo.validation;

import com.example.demo.dto.UserDto;
import com.example.demo.exception.UserNotFoundException;
import com.example.demo.persistance.user.Role;
import com.example.demo.persistance.user.User;
import com.example.demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

import static com.example.demo.persistance.user.Role.ADMIN;

@Component
@RequiredArgsConstructor
public class UserValidator {

    private final UserRepository repository;

    public void validateCreation(UserDto userDto){
        validateRole(userDto.getRole());
        validateId(userDto.getId());
    }

    public void validateUpdate(UserDto userDto){
        validateExistence(userDto.getId());
        validateEmail(userDto.getEmail());
    }

    public void validateRemoval(UUID id){
        User user = validateExistence(id);
        validateRole(user.getRole());
    }

    private User validateExistence(UUID id){
        return repository.findById(id)
                .orElseThrow(UserNotFoundException::new);
    }

    private void validateRole(Role role){
        if(role == ADMIN){
            throw new UnsupportedOperationException("The creation/deletion of  user with role 'ADMIN' is not allowed!");
        }
    }

    private void validateId(UUID id){
        if(repository.findById(id).isPresent()){
            throw new IllegalArgumentException("There  is user registered with such id!");
        }
    }

    private void validateEmail(String email){
        if(repository.findByEmail(email).isPresent()){
            throw new IllegalArgumentException("There  is user registered with such email!");
        }
    }

}
