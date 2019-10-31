package com.vmf.flat.files.exception;

public class FatalFailureException extends RuntimeException {
    public FatalFailureException(String message, Throwable cause) {
        super(message, cause);
    }
}
