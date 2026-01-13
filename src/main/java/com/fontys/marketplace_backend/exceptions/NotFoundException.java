package com.fontys.marketplace_backend.exceptions;

public class NotFoundException extends RuntimeException {
    public NotFoundException() {
        super("Unknown error happend");
    }

    public NotFoundException(String message) {
        super(message);
    }
}
