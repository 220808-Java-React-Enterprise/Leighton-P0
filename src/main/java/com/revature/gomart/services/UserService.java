package com.revature.gomart.services;

import com.revature.gomart.daos.UserDAO;
import com.revature.gomart.models.User;
import com.revature.gomart.utils.custom_exceptions.*;

import java.util.*;

public class UserService {

    private final UserDAO userDAO;

    public UserService(UserDAO userDAO) {this.userDAO = userDAO;}

    public void register(User user) {userDAO.save(user);}

    public User login(String username, String password) {
        User user = userDAO.getByUsernameAndPassword(username, password);
        if (user == null) throw new InvalidUserException("User not found");
        return user;
    }

    public User findById(String id) {
        User user = userDAO.getById(id);
        if (user == null) throw new InvalidUserException("User not found");
        return user;
    }

    public void updateUser(User user) {userDAO.update(user);}

    public boolean isValidUsername(String username) {
        if (!username.matches("^[a-zA-Z0-9_-]{3,15}$")) throw new InvalidUserException("\nUsername must be between 3-15 characters and may only contain letters, numbers, dashes, and hyphens");
        return true;
    }

    public boolean isValidTitle(String title) {
        if (!title.matches("^(\\s)*[A-Za-z]{0,30}((\\s)?((\\'|\\-|\\.)?([A-Za-z])+))*(\\s)*$")) throw new InvalidUserException("\nPlease enter a valid title (up to 30 characters)");
        return true;
    }
    public boolean isValidName(String name) {
        if (!name.matches("^(\\s)*[A-Za-z]{1,20}((\\s)?((\\'|\\-|\\.)?([A-Za-z])+))*(\\s)*$")) throw new InvalidUserException("\nPlease enter your name (max 20 characters)");
        return true;
    }

    // Chris email regex [A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\.[A-Za-z]{2,4}
    public boolean isValidEmail(String email) {
        if (!email.matches("^[a-zA-Z0-9]+(?:\\.[a-zA-Z0-9]+)*@[a-zA-Z0-9]+(?:\\.[a-zA-Z0-9]+)*$")) throw new InvalidUserException("\nPlease enter a valid email address");
        return true;
    }

    public boolean isValidPassword(String password) {
        if (!password.matches("^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$ %^&*-]).{8,20}$")) throw new InvalidUserException("/Password must be 8-20 characters with at least one uppercase letter, one lowercase letter, one number, and one special character");
        return true;
    }

    public boolean isSamePassword(String password, String password2) {
        if (!password2.equals(password)) throw new InvalidUserException("Passwords do not match!");
        return true;
    }

    public boolean isValidKantoTown(String town) {
        List<String> kantoTowns = Arrays.asList("Pallet Town", "Viridian City", "Pewter City", "Cerulean City", "Vermilion City", "Lavender Town", "Celadon City", "Fuchsia City", "Saffron City", "Cinnabar Island", "Indigo Plateau");
        if (!kantoTowns.contains(town)) throw new InvalidUserException("Town not recognized");
        return true;
    }

    public boolean isDuplicateUsername(String username) {
        if (userDAO.getUsername(username) != null) throw new InvalidUserException("\nUsername not available!");
        return true;
    }

    public boolean isDuplicateEmail(String email) {
        if (userDAO.getUsername(email) != null) throw new InvalidUserException("\nUsername not available!");
        return true;
    }

    public boolean isValidStreetAddress(String address) {
        if (!address.matches("\\w+(\\s\\w+){2,}")) throw new InvalidUserException("\nStreet address not recognized");
        return true;
    }
}
