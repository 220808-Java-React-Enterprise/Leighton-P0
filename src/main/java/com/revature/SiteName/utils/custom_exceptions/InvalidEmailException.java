package com.revature.SiteName.utils.custom_exceptions;

public class InvalidEmailException extends RuntimeException {
    public InvalidEmailException() {
    }

    public InvalidEmailException(String message) {super(message);}
}
