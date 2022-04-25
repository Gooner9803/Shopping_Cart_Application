package com.example.demo.exception;

public class UserNotFoundException extends RuntimeException{

    public UserNotFoundException() {
        super("User with such id not found!");
    }

    public UserNotFoundException(String message) {
        super(message);
    }
}
