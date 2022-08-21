package com.revature.gomart.ui;

import com.revature.gomart.daos.*;
import com.revature.gomart.models.Manager;
import com.revature.gomart.models.User;
import com.revature.gomart.services.*;
import com.revature.gomart.utils.custom_exceptions.*;

import java.util.Scanner;
import java.util.UUID;

public class AdminLogin implements MenuIF{

    private final UserService userService;

    public AdminLogin(UserService userService) {this.userService = userService;}
    @Override
    public void start() {
        String userInput = "";
        Scanner scan = new Scanner(System.in);

        exit:
        {
            while (true) {
                System.out.println("\nManager menu");
                System.out.println("Press one of the following keys to continue:");
                System.out.println("[1] Login to an existing manager account");
                System.out.println("[2] Register as a new manager");
                System.out.println("[3] Exit the Pokemart");

                System.out.println("\nEnter:");
                userInput = scan.nextLine();

                switch (userInput) {
                    case "1":
                        login();
                        break;
                    case "2":
                        Manager manager = signup();
                        new AdminMenu(manager, new UserService(new UserDAO())).start();
                        break;
                    case "3":
                        System.out.println("We hope to see you again!");
                        break exit;

                }
            }
        }
    }

    private void login() {
        String username = "";
        String password = "";
        Scanner scan = new Scanner(System.in);

        System.out.println("Signing in existing user");

        exit:
        {
            while (true) {
                System.out.println("Please enter your username: ");
                username = scan.nextLine();

                System.out.println("Please enter your password: ");
                password = scan.nextLine();

                try {
                    User manager = userService.login(username, password);
                    new LandingPage(manager, new UserService(new UserDAO()), new ProductService(new ProductDAO()), new OrderService(new OrderDAO()), new OPService(new OpDAO()), new AddressService(new AddressDAO())).start();
                } catch (InvalidUserException e) {
                    System.out.println(e.getMessage());
                }
            }
        }
    }

    private Manager signup() {
        String fname = "";
        String username = "";
        String password = "";
        String password2 = "";
        String email = "";

        Manager manager;
        Scanner scan = new Scanner(System.in);

        System.out.println("Create new admin account:");

        exit:
        {
            while (true) {
                fnameExit:
                {
                    while (true) {
                        System.out.println("Please enter your name: ");
                        fname = scan.nextLine();

                        try {
                            userService.isValidName(fname);
                            break fnameExit;
                        } catch (InvalidUserException e) {
                            System.out.println(e.getMessage());
                        }
                    }
                }
                usernameExit:
                {
                    while (true) {
                        System.out.println("Please create a username (8-20 characters)");
                        username = scan.nextLine();

                        try {
                            userService.isValidUsername(username);
                            // userService.isDuplicateUsername(username);
                            break usernameExit;
                        } catch (InvalidUserException e) {
                            System.out.println(e.getMessage());
                        }
                    }
                }
                passwordExit:
                {
                    while (true) {
                        try {
                            System.out.println("Please create a password (Must contain: 1 uppercase letter, 1 lowercase letter, 1 number, one special character, 8-20 characters");
                            password = scan.nextLine();

                            userService.isValidPassword(password);

                            System.out.println("Reenter your new password: ");
                            password2 = scan.nextLine();

                            userService.isSamePassword(password2, password);
                            break passwordExit;
                        } catch (InvalidUserException e) {
                            System.out.println(e.getMessage());
                        }
                    }
                }
                emailExit:
                {
                    while (true) {
                        System.out.println("Please enter your email");
                        email = scan.nextLine();

                        try {
                            userService.isValidEmail(email);
                            break emailExit;
                        } catch (InvalidUserException e) {
                            System.out.println(e.getMessage());
                        }
                    }
                }
                confirmExit:
                {
                    while (true) {
                        System.out.println("Please review the following information: ");
                        System.out.println("You are " + fname);
                        System.out.println("Username: " + username + "\nPassword: " + password + "\nEmail" + email);
                        System.out.println("Is this correct? (y/n):");

                        switch (scan.nextLine().toLowerCase()) {
                            case "y":
                                manager = new Manager(UUID.randomUUID().toString(), fname, username, password, email);
                                return manager;
                            case "n":
                                System.out.println("\nPlease try signing up again");
                                break confirmExit;
                            default:
                                System.out.println("\nResponse not recognized");
                        }
                    }
                }
            }
        }
    }

}
