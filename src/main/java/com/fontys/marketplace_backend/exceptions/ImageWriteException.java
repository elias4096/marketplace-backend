package com.fontys.marketplace_backend.exceptions;

public class ImageWriteException extends RuntimeException {
    public ImageWriteException() {
        super("Unknown error happend");
    }

    public ImageWriteException(String message) {
        super(message);
    }
}
