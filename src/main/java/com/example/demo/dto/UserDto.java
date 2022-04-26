package com.example.demo.dto;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

import com.example.demo.persistance.user.Role;
import com.example.demo.validation.constraint.Age;
import com.example.demo.validation.constraint.Name;
import com.example.demo.validation.constraint.ValidPassword;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserDto {

    @NotNull
    private UUID id;

    @Name(message = "Invalid name!")
    private String name;

    @Name(message = "Invalid surname!")
    private String surname;

    @NotNull
    @Age(min = 18, max = 64)
    private LocalDate birthDate;

    @NotNull
    @Enumerated(EnumType.STRING)
    private Role role;

    @NotNull
    @Email
    private String email;

    @NotNull
    @ValidPassword
    @JsonInclude(value = NON_NULL)
    private String password;
}
