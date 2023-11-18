package com.user.service.userservice.exceptions;

public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException() {
        super("Resource Not Found Exception!!");
    }

    public ResourceNotFoundException(String msg) {
        super(msg);
    }
}
