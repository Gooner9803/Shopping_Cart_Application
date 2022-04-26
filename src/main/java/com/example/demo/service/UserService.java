package com.example.demo.service;

import com.example.demo.dto.UserDto;
import com.example.demo.exception.UserNotFoundException;
import com.example.demo.mapper.UserMapper;
import com.example.demo.persistance.user.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.validation.UserValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {

    private final UserRepository repository;
    private final UserMapper mapper;
    private final UserValidator validator;

    private final PasswordEncoder encoder;


    public List<UserDto> getAll(){
        return repository.findAll().stream()
                .map(mapper::mapToDto)
                .collect(Collectors.toList());
    }

    public UserDto getById(UUID id){
        User user = repository.findById(id)
                .orElseThrow(UserNotFoundException::new);

        return mapper.mapToDto(user);
    }

    public UserDto create(UserDto userDto){
        validator.validateCreation(userDto);
        User user = repository.save(mapper.mapToEntity(userDto));
        user.setName(encoder.encode(user.getName()));

        return mapper.mapToDto(user);
    }

    public UserDto update(UserDto userDto){
        validator.validateUpdate(userDto);
        User user = repository.save(mapper.mapToEntity(userDto));

        return mapper.mapToDto(user);
    }

    public void deleteById(UUID id){
        validator.validateRemoval(id);

        repository.deleteById(id);
    }

    public User findById(UUID id){
        return repository.findById(id)
                .orElseThrow(UserNotFoundException::new);
    }
}
