package com.example.atelierapp.exceptions;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String message) {
        super("resource not found exception - " + message);
    }
}

