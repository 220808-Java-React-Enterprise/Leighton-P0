package com.revature.gomart.services;

import com.revature.gomart.daos.UserDAO;
import com.revature.gomart.models.User;
import com.revature.gomart.utils.custom_exceptions.InvalidUserException;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
//import org.junit.framework.TestCase;
import org.mockito.Mockito;

import static org.mockito.Mockito.*;

public class UserServiceTest {

    private UserService sut;

    private final UserDAO mockUserDao = mock(UserDAO.class);

    @Before
    public void setup() {sut = new UserService(mockUserDao);}

    @Test
    public void test_isValidUsername_withCorrectUsername() {
        String validUsername = "AshKetchum1999";

        boolean flag = sut.isValidUsername(validUsername);

        Assert.assertTrue(flag);
    }

    @Test(expected = InvalidUserException.class)
    public void test_isInvalidUsername_withIncorrectUsername() {
        String invalidUsername = "Oak@PalletTown";

        sut.isValidUsername(invalidUsername);
    }

    @Test(expected = InvalidUserException.class)
    public void test_isInvalidUsername_withEmptyUsername() {
        String invalidUsername = "";

        sut.isValidUsername(invalidUsername);
    }

    @Test
    public void test_isValidEmail_withValidEmail() {
        String validEmail = "misty@ceruleangym.com";

        sut.isValidEmail(validEmail);
    }

    @Test(expected = InvalidUserException.class)
    public void test_isInvalidEmail_withInvalidEmail() {
        String invalidEmail = "gary%oak@pallettown.com";

        sut.isValidEmail(invalidEmail);
    }

    @Test(expected = InvalidUserException.class)
    public void test_isInvalidEmail_withEmptyEmail() {
        String invalidEmail = "";

        sut.isValidEmail(invalidEmail);
    }

    @Test
    public void test_isValidPassword_withValidPassword() {
        String validPassword = "P@ssw0rd";

        sut.isValidPassword(validPassword);
    }

    @Test(expected = InvalidUserException.class)
    public void test_isInvalidPassword_withInvalidPassword() {
        String invalidPassword = "1234567890abcdefg";

        sut.isValidPassword(invalidPassword);
    }

    @Test(expected = InvalidUserException.class)
    public void test_isInvalidPassword_withEmptyPassword() {
        String invalidPassword = "";

        sut.isValidEmail(invalidPassword);
    }

    @Test
    public void test_isValidAddress_withValidAddress() {
        String validAddress = "123 Oran Way";

        sut.isValidStreetAddress(validAddress);
    }

    @Test(expected = InvalidUserException.class)
    public void test_isInvalidAddress_withInvalidAddress() {
        String invalidAddress = "123 Oran";

        sut.isValidStreetAddress(invalidAddress);
    }

    @Test(expected = InvalidUserException.class)
    public void test_isInvalidAddress_withEmptyAddress() {
        String invalidAddress = "";

        sut.isValidStreetAddress(invalidAddress);
    }
}
