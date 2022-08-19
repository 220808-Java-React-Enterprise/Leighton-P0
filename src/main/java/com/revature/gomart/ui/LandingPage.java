package com.revature.gomart.ui;

import com.revature.gomart.models.User;
import com.revature.gomart.services.UserService;
import com.revature.gomart.daos.ProductDAO;

public class LandingPage implements MenuIF {

    private final User user;

    private final UserService userService;

    public LandingPage(User user, UserService userService) {
        this.user = user;
        this.userService = userService;
    }

    @Override
    public void start() {
        System.out.println("\nWelcome back, " + user.getTitle() + " " + user.getFname() + "\nWe're still getting things set up, please be patient as we prepare a great experience for you!");
        System.out.println("We hope to see you again!");


    }

    private void displayMeds() {

    }

    private void displayPokeballs() {

    }

    private void displayFieldItems() {

    }
}
