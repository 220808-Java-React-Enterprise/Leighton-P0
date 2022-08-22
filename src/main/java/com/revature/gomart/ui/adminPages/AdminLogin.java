package com.revature.gomart.ui.adminPages;

import com.revature.gomart.models.*;
import com.revature.gomart.services.*;
import com.revature.gomart.ui.LoginMenu;
import com.revature.gomart.ui.MenuIF;
import com.revature.gomart.utils.custom_exceptions.*;

import java.util.Scanner;
import java.util.UUID;

public class AdminLogin extends PageServices implements MenuIF {

    public AdminLogin(UserService userService, ProductService productService, OrderService orderService, OPService opService, AddressService addressService) {
        super(userService, productService, orderService, opService, addressService);
    }

    @Override
    public void start() {
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

                switch (scan.nextLine()) {
                    case "1":
                        login();
                        break exit;
                    case "2":
                        Manager manager = signup();
                        new AdminMenu(manager, userService, productService, orderService, opService, addressService).start();
                        break exit;
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

        System.out.println("\nSigning in existing user");

        exit:
        {
            while (true) {
                System.out.println("\nPlease enter your username: ");
                username = scan.nextLine();

                System.out.println("\nPlease enter your password: ");
                password = scan.nextLine();

                try {
                    User manager = userService.login(username, password);
                    userService.isValidAdmin(username, password);
                    new AdminMenu(manager, userService, productService, orderService, opService, addressService).start();

                } catch (InvalidUserException e) {
                    System.out.println(e.getMessage());
                    if (userService.isValidAdmin(username, password)) {
                        System.out.println("1. Try again");
                        System.out.println("2. Log in as a customer");
                        System.out.println("3. Back to manager menu");
                        switch (scan.nextLine()) {
                            case "1":
                                login();
                                break exit;
                            case "2":
                                new LoginMenu(userService, productService, orderService, opService, addressService).login();
                                break exit;
                            case "3":
                                new AdminLogin(userService, productService, orderService, opService, addressService);
                                break exit;
                            default:
                                System.out.println("Input not recognized");
                        }
                    }
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
                System.out.println("Please review the following information: ");
                System.out.println("You are " + fname);
                System.out.println("Username: " + username + "\nPassword: " + password + "\nEmail" + email);
                System.out.println("Is this correct? (y/n):");

                switch (scan.nextLine().toLowerCase()) {
                    case "y":
                        manager = new Manager(UUID.randomUUID().toString(), fname, username, password, email);
                        return manager;
                    case "n":
                        manager = new Manager(UUID.randomUUID().toString(), fname, username, password, email);
                        System.out.println("\nPlease try signing up again");
                        return updateInfo(manager);
                    default:
                        System.out.println("\nResponse not recognized");
                }
            }
        }
    }

    private Manager updateInfo(Manager manager) {
        Scanner scan = new Scanner(System.in);

        exit:
        {
            while (true) {
                System.out.println("What would you like to change? ");
                System.out.println(
                        "\n1. Name: " + manager.getFname() +
                                "\n2. Username: " + manager.getUsername() +
                                "\n3. Password: " + manager.getPassword() +
                                "\n4. Email: " + manager.getEmail() +
                                "\nx. Go back");
                String input = scan.nextLine();

                choiceExit:
                {
                    while (true) {
                        switch (input) {
                            case "1":
                                while (true) {
                                    System.out.println("\nPlease enter your name: ");
                                    String fname = scan.nextLine();

                                    try {
                                        userService.isValidName(fname);
                                        manager.setFname(fname);
                                        break choiceExit;
                                    } catch (InvalidUserException e) {
                                        System.out.println(e.getMessage());
                                    }
                                }
                            case "2":
                                while (true) {
                                    System.out.println("\nPlease create a username (3-15 characters): ");
                                    String username = scan.nextLine();

                                    try {
                                        userService.isValidUsername(username);
                                        userService.isDuplicateUsername(username);
                                        manager.setUsername(username);
                                        break choiceExit;
                                    } catch (InvalidUserException e) {
                                        System.out.println(e.getMessage());
                                    }
                                }
                            case "3":
                                while (true) {
                                    try {
                                        System.out.println("\nPlease create a password (Must contain: 1 uppercase letter, 1 lowercase letter, 1 number, one special character, 8-20 characters): ");
                                        String password = scan.nextLine();

                                        userService.isValidPassword(password);

                                        System.out.println("\nReenter your new password: ");
                                        String password2 = scan.nextLine();

                                        userService.isSamePassword(password2, password);

                                        manager.setUsername(password);
                                        break choiceExit;
                                    } catch (InvalidUserException e) {
                                        System.out.println(e.getMessage());
                                    }
                                }
                            case "4":
                                while (true) {
                                    System.out.println("\nPlease enter your email: ");
                                    String email = scan.nextLine();

                                    try {
                                        userService.isValidEmail(email);
                                        userService.isDuplicateEmail(email);

                                        manager.setEmail(email);
                                        break choiceExit;
                                    } catch (InvalidUserException e) {
                                        System.out.println(e.getMessage());
                                    }
                                }
                            case "x":
                                break exit;
                            default:
                                System.out.println("\nResponse not recognized");

                        }
                    }
                }
            }
        }
        return manager;
    }
}
