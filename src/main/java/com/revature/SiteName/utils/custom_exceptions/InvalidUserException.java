package com.revature.SiteName.utils.custom_exceptions;

public class InvalidUserException extends RuntimeException {
    public InvalidUserException() {
    }

    public InvalidUserException(String message) {super(message);}
}

