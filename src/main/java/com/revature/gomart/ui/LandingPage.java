package com.revature.gomart.ui;

import com.revature.gomart.models.User;
import com.revature.gomart.services.UserService;

public class LandingPage implements MenuIF {

    private final User user;

    private final UserService userService;

    public LandingPage(User user, UserService userService) {
        this.user = user;
        this.userService = userService;
    }

    @Override
    public void start() {
        System.out.println("\nWelcome back " + user.getTitle() + " " + user.getFname());
    }
}
