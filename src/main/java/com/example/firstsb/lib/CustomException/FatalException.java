package com.example.firstsb.lib.CustomException;

public class FatalException extends RuntimeException {
    public FatalException(String message) {
        super(message);
    }
}
