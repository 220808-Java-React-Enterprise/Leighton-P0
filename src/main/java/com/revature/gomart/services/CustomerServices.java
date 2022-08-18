package com.revature.gomart.services;

import com.revature.gomart.utils.custom_exceptions.InvalidUserException;
import com.revature.gomart.utils.custom_exceptions.InvalidNameException;
import com.revature.gomart.utils.custom_exceptions.InvalidEmailException;

public class CustomerServices {

    public void isValidUsername(String username) {
        if (!username.matches("/^[A-Za-z0-9]+(?:[ _-][A-Za-z0-9]+)*$/")) throw new InvalidUserException("\nUsername may only contain letters,numbers, dashes, and hyphens (separators)\n.No more than one separator in a row allowed\nSeparators not allowed at beginning or end of username");
    }

    public void isValidName(String name) {
        if (!name.matches("^(\\s)*[A-Za-z]+((\\s)?((\\'|\\-|\\.)?([A-Za-z])+))*(\\s)*$")) throw new InvalidNameException("\nPlease enter your name");
    }

    public void isValidEmail(String email) {
        if (!email.matches("^[a-zA-Z0-9]+(?:\\.[a-zA-Z0-9]+)*@[a-zA-Z0-9]+(?:\\.[a-zA-Z0-9]+)*$")) throw new InvalidEmailException(("\nPlease enter a valid email address"));
    }
}
