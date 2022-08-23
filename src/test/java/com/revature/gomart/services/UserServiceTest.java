package com.revature.gomart.services;

import com.revature.gomart.daos.UserDAO;
import com.revature.gomart.models.*;
import com.revature.gomart.utils.custom_exceptions.InvalidUserException;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
//import org.junit.framework.TestCase;
import org.mockito.Mockito;

import static org.mockito.Mockito.*;

public class UserServiceTest {

    // Imports user services
    private UserService sut;

    // Creates a mock database
    private final UserDAO mockUserDao = mock(UserDAO.class);

    // Links the mock database to the user services
    @Before
    public void setup() {sut = new UserService(mockUserDao);}

    // Tests whether a username is valid
    // this username has a disallowed symbol
    // Usernames can only contain the special characters _ and -
    @Test(expected = InvalidUserException.class)
    public void test_isInvalidUsername_withIncorrectUsername() {
        String invalidUsername = "Brock@Pewter";

        sut.isValidUsername(invalidUsername);
    }

    // Tests whether an email is valid
    // this email is not properly formatted to the regex
    @Test(expected = InvalidUserException.class)
    public void test_isInvalidEmail_withInvalidEmail() {
        String invalidEmail = "@garyoak.pallettown.com";

        sut.isValidEmail(invalidEmail);
    }

    // Tests the validity of a password
    // This password does not meet regex character requirements
    // Passwords must also contain an uppercase letter and a special character
    @Test(expected = InvalidUserException.class)
    public void test_isInvalidPassword_withInvalidPassword() {
        String invalidPassword = "1234567890abcdefg";

        sut.isValidPassword(invalidPassword);
    }

    // Tests the validity of the street address input
    // This street address is valid
    @Test
    public void test_isValidAddress_withValidAddress() {
        String validAddress = "123 Oran Way";

        sut.isValidStreetAddress(validAddress);
    }

    // This street address does not meet regex repetition requirements
    // It should have at least three groupings of characters
    @Test(expected = InvalidUserException.class)
    public void test_isInvalidAddress_withInvalidAddress() {
        String invalidAddress = "123 Oran";

        sut.isValidStreetAddress(invalidAddress);
    }

    // Tests the validity of the hometown input
    // This item is not properly formatted
    // It should be formatted as "Pallet Town"
    @Test(expected = InvalidUserException.class)
    public void test_isInvalidHometown_withIncorrectFormat() {
        String invalidAddress = "pallet";

        sut.isValidStreetAddress(invalidAddress);
    }

    // Tests the login function
    // This input successfully logs in a user
    @Test
    public void test_login_ValidLoginGivenCorrectCredentials() {
        UserService spiedSut = Mockito.spy(sut);
        String validUsername = "coolleighton";
        String validPassword = "P@ssw0rd";

        when(spiedSut.isValidUsername(validUsername)).thenReturn(true);
        when(spiedSut.isValidPassword(validPassword)).thenReturn(true);
        when(mockUserDao.getByUsernameAndPassword(validUsername, validPassword)).thenReturn(new User());

        User user = spiedSut.login(validUsername, validPassword);

        Assert.assertNotNull(user);

        verify(mockUserDao, times(1)).getByUsernameAndPassword(validUsername, validPassword);
    }

    // Tests the Find User By ID function
    // This input is not an existing user id
    @Test(expected = InvalidUserException.class)
    public void test_findById_InvalidGivenInput() {
        String invalidId = "invalid";

        when(mockUserDao.getById(invalidId)).thenReturn(null);

        sut.findById(invalidId);
    }

    // Tests admin login
    // The Customer class has a default "admin" attribute of "false"
    // User must have "admin" parameter of "true"
    @Test(expected = InvalidUserException.class)
    public void test_isValidAdmin_GivenAdminEqualsFalse() {
        UserService spiedSut = Mockito.spy(sut);
        String validUsername = "username";
        String validPassword = "P@ssw0rd";

        when(spiedSut.isValidUsername(validUsername)).thenReturn(true);
        when(spiedSut.isValidPassword(validPassword)).thenReturn(true);
        when(mockUserDao.getByUsernameAndPassword(validUsername,validPassword)).thenReturn(new Customer());

        boolean isAdmin = spiedSut.isValidAdmin(validUsername,validPassword);

        Assert.assertNotNull(isAdmin);

        verify(mockUserDao, times(1)).getAdmin(validUsername,validPassword);
    }

    // Tests whether a username exists in the database or not
    // This input already exists in the database
    @Test(expected = InvalidUserException.class)
    public void test_isDuplicateUsername_GivenDuplicateUsername() {
        UserService spiedSut = Mockito.spy(sut);
        String validUsername = "username";
        String validPassword = "P@ssw0rd";

        String duplicateUsername = "username";

        when(spiedSut.isValidUsername(validUsername)).thenReturn(true);
        when(spiedSut.isValidPassword(validPassword)).thenReturn(true);
        when(spiedSut.isValidPassword(duplicateUsername)).thenReturn(true);
        when(mockUserDao.getByUsernameAndPassword(validUsername,validPassword)).thenReturn(new User());

        User user = spiedSut.login(validUsername, validPassword);

        Assert.assertNotNull(user);

        verify(mockUserDao, times(1)).getUsername(duplicateUsername);
    }
}
