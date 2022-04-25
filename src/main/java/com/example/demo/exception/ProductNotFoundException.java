package com.example.demo.exception;

public class ProductNotFoundException extends RuntimeException{

    public ProductNotFoundException() {
        super("Product with such id not found!");
    }

    public ProductNotFoundException(String message) {
        super(message);
    }
}
