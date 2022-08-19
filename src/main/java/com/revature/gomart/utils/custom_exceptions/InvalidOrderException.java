package com.revature.gomart.utils.custom_exceptions;

public class InvalidOrderException extends RuntimeException {
    public InvalidOrderException() {}

    public InvalidOrderException(String message) {super(message);}
}
