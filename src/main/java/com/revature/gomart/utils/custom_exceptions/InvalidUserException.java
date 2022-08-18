package com.revature.gomart.utils.custom_exceptions;

public class InvalidUserException extends RuntimeException {
    public InvalidUserException() {
    }

    public InvalidUserException(String message) {super(message);}
}

