package com.revature.SiteName.ui;

import com.revature.SiteName.models.User;

public class LandingPage implements MenuIF {

    private final User user;

    public LandingPage(User user) {this.user = user;}

    @Override
    public void start() {
        System.out.println("\nWelcome back " + user.getName());
    }
}
