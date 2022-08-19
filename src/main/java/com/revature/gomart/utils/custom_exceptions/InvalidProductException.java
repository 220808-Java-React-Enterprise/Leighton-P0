package com.revature.gomart.utils.custom_exceptions;

public class InvalidProductException extends RuntimeException{

    public InvalidProductException() {}
    public InvalidProductException(String message) {super(message);}
}
