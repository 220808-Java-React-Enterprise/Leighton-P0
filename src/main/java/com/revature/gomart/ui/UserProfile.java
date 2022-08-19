package com.revature.gomart.ui;

import com.revature.gomart.models.User;
import com.revature.gomart.services.UserService;

public class UserProfile implements MenuIF{

    private final User user;

    private final UserService userService;

    public UserProfile(User user, UserService userService) {
        this.user = user;
        this.userService = userService;
    }

    @Override
    public void start() {
        System.out.println("Welcome to your profile! We will have functionality available for you shortly!");
    }
}
