package com.example.demo.exception;

public class OrderNotFoundException extends RuntimeException{

    public OrderNotFoundException() {
        super("Order with such id not found!");
    }

    public OrderNotFoundException(String message) {
        super(message);
    }
}
