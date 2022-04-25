package com.example.demo;

import com.example.demo.persistance.Role;
import com.example.demo.persistance.User;
import com.example.demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
@RequiredArgsConstructor
public class ShoppingCartApplication {

    private final UserRepository repo;
    private final PasswordEncoder encoder;

    public static void main(String[] args) {
        SpringApplication.run(ShoppingCartApplication.class, args);
    }

    @Profile("test")
    @Bean
    CommandLineRunner runner(){
        return args -> {
          User user = new User(null, "admin", "admin", null, Role.ADMIN, "admin@mail.ru", encoder.encode("admin"),true);
          repo.save(user);
        };
    }

}
