package com.revature.SiteName.ui;

import com.revature.SiteName.models.User;
import com.revature.SiteName.services.UserServices;
import com.revature.SiteName.utils.custom_exceptions.InvalidUserException;

import java.util.Scanner;

public class LoginMenu implements MenuIF {

    @Override
    public void start() {
        String userInput = "";
        Scanner scan = new Scanner(System.in);

        exit:
        {
            while (true) {
                System.out.println("\nWelcome to the Pokemart");
                System.out.println("Press one of the following keys to continue:");
                System.out.println("[1] Login to an existing account");
                System.out.println("[2] Create a new account");
                System.out.println("[3] Exit the Pokemart");

                System.out.println("\nEnter:");
                userInput = scan.nextLine();

                switch (userInput) {
                    case "1":
                        login();
                        break;
                    case "2":
                        User user = signup();
                        new LandingPage(user).start();
                        break;
                    case "3":
                        System.out.println("We hope to see you again!");
                        break exit;
                    default:
                        System.out.println("\nSorry, I didn't catch that\nPlease select either 1, 2, or 3");
                }
            }
        }
    }

    private void login() {
        System.out.println("\nThank you for logging in");
    }

    private User signup() {
        String name = "";
        String username = "";
        String password = "";
        String email = "";
        String hometown = "";
        String region = "";

        User user = new User();
        UserServices userService = new UserServices();

        return user;
    }
}
