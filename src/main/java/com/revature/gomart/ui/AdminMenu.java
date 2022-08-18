package com.revature.gomart.ui;

import com.revature.gomart.models.User;
import com.revature.gomart.services.UserService;

public class AdminMenu implements MenuIF {

    private final User user;

    private final UserService userService;

    public AdminMenu (User user, UserService userService) {
        this.user = user;
        this.userService = userService;
    }

    @Override
    public void start() {
        System.out.println("Welcome in, " + user.getFname());
    }
}
