package com.revature.gomart.ui;

import com.revature.gomart.daos.UserDAO;
import com.revature.gomart.models.Customer;
import com.revature.gomart.models.User;
import com.revature.gomart.services.UserService;
import com.revature.gomart.utils.custom_exceptions.*;

import java.util.Scanner;
import java.util.UUID;

public class LoginMenu implements MenuIF {

    private final UserService userService;

    public LoginMenu(UserService userService) {this.userService = userService;}

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
                        Customer customer = signup();
                        userService.register(customer);
                        new LandingPage(customer, new UserService(new UserDAO())).start();
                        break;
                    case "3":
                        System.out.println("We hope to see you again!");
                        break exit;
                    case "+":
                        new AdminLogin(new UserService(new UserDAO())).start();
                    default:
                        System.out.println("\nSorry, I didn't catch that\nPlease select either 1, 2, or 3");
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
                    User customer = userService.login(username, password);
                    new LandingPage(customer, new UserService(new UserDAO())).start();
                } catch (InvalidUserException e) {
                    System.out.println(e.getMessage());
                }
            }
        }
    }

    private Customer signup() {
        String title = "";
        String fname = "";
        String username = "";
        String password = "";
        String password2 = "";
        String email = "";
        String hometown = "";

        Customer customer;
        Scanner scan = new Scanner(System.in);

        System.out.println("Create your new account!");

        exit:
        {
            while (true) {
                titleExit:
                {
                    while (true) {
                        System.out.println("Please enter your title: ");
                        title = scan.nextLine();

                        try {
                            userService.isValidTitle(title);
                            break titleExit;
                        } catch (InvalidUserException e) {
                            System.out.println(e.getMessage());
                        }
                    }
                }
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
                hometownExit:
                {
                    while (true) {
                        System.out.println("Please enter your hometown");
                        hometown = scan.nextLine();

                        try {
                            userService.isValidKantoTown(hometown);
                            break hometownExit;
                        } catch (InvalidUserException e) {
                            System.out.println(e.getMessage());
                        }
                    }
                }
                confirmExit:
                {
                    while (true) {
                        System.out.println("Please review the following information: ");
                        System.out.println("You are " + title + " " + fname);
                        System.out.println("Username: " + username + "\nPassword: " + password + "\nEmail" + email + "\nAnd you are from " + hometown);
                        System.out.println("Is this correct? (y/n):");

                        switch (scan.nextLine().toLowerCase()) {
                            case "y":
                                customer = new Customer(UUID.randomUUID().toString(), title, fname, username, password, email, hometown);
                                return customer;
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
