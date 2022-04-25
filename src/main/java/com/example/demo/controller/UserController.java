package com.example.demo.controller;

import com.example.demo.dto.UserDto;
import com.example.demo.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;


import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService service;

    @Operation(summary = "Returns all users.")
    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<UserDto>> getAll(){
        return ResponseEntity.ok().body(service.getAll());
    }

    @Operation(summary = "Returns user by id.")
    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<UserDto> getById(@PathVariable UUID id){
        return ResponseEntity.ok().body(service.getById(id));
    }

    @Operation(summary = "Creates and returns new customer.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Creates new customer while all fields are valid."),
            @ApiResponse(responseCode = "400", description = "Customer's fields are not valid or either email or id already reserved!.")})
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<UserDto> create(@Valid @RequestBody UserDto userDto){
        return ResponseEntity.ok().body(service.create(userDto));
    }

    @Operation(summary = "Updates user.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Updates customer while all fields are valid."),
            @ApiResponse(responseCode = "400", description = "User's fields are not valid or either email or id already reserved!.")})
    @PutMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<UserDto> update(@Valid @RequestBody UserDto userDto){
        return ResponseEntity.ok().body(service.update(userDto));
    }

    @Operation(summary = "Deletes customer by id.")
    @DeleteMapping ("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteById(@PathVariable UUID id) {
        service.deleteById(id);

        return ResponseEntity.ok().build();
    }
}
