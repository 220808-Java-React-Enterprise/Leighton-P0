package com.revature.gomart.ui;

import com.revature.gomart.daos.*;
import com.revature.gomart.models.Customer;
import com.revature.gomart.models.Order;
import com.revature.gomart.models.User;
import com.revature.gomart.services.*;
import com.revature.gomart.utils.custom_exceptions.*;

import java.util.Scanner;
import java.util.UUID;

public class LoginMenu implements MenuIF {

    private final UserService userService;

    private final OrderService orderService;

    public LoginMenu(UserService userService, OrderService orderService) {
        this.userService = userService;
        this.orderService = orderService;
    }

    @Override
    public void start() {
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

                switch (scan.nextLine()) {
                    case "1":
                        login();
                        break exit;
                    case "2":
                        Customer customer = signup();
                        Order order = new Order(UUID.randomUUID().toString(), customer.getId());
                        userService.register(customer);
                        orderService.createNew(order);
                        new LandingPage(customer, new UserService(new UserDAO()), new ProductService(new ProductDAO()), new OrderService(new OrderDAO()), new OPService(new OpDAO())).start();
                        break exit;
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
                    User user = userService.login(username, password);

                    Order order = orderService.retrieve(false, user.getId());
                    if (order == null) {
                        System.out.println("Creating a cart for you");
                        Order o = new Order(UUID.randomUUID().toString(), user.getId());
                        orderService.createNew(o);
                    }

                    new LandingPage(user, new UserService(new UserDAO()), new ProductService(new ProductDAO()), new OrderService(new OrderDAO()), new OPService(new OpDAO())).start();


                    break exit;
                } catch (InvalidUserException e) {
                    System.out.println(e.getMessage());
                    System.out.println("1. Try again");
                    System.out.println("2. Create an account");
                    switch (scan.nextLine()) {
                        case "1":
                            login();
                            break exit;
                        case "2":
                            Customer customer = signup();
                            userService.register(customer);
                            new LandingPage(customer, new UserService(new UserDAO()), new ProductService(new ProductDAO()), new OrderService(new OrderDAO()), new OPService(new OpDAO())).start();
                            break exit;
                        default:
                            System.out.println("Invalid input");
                    }
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
                        System.out.println("Please create a username (3-15 characters): ");
                        username = scan.nextLine();

                        try {
                            userService.isValidUsername(username);
                            userService.isDuplicateUsername(username);
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
                            System.out.println("Please create a password (Must contain: 1 uppercase letter, 1 lowercase letter, 1 number, one special character, 8-20 characters): ");
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
                        System.out.println("Please enter your email: ");
                        email = scan.nextLine();

                        try {
                            userService.isValidEmail(email);
                            userService.isDuplicateEmail(email);
                            break emailExit;
                        } catch (InvalidUserException e) {
                            System.out.println(e.getMessage());
                        }
                    }
                }
                hometownExit:
                {
                    while (true) {
                        System.out.println("Please enter your hometown: ");
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
                        System.out.println("Username: " + username + "\nPassword: " + password + "\nEmail: " + email + "\nAnd you are from " + hometown);
                        System.out.println("Is this correct? (y/n):");

                        switch (scan.nextLine().toLowerCase()) {
                            case "y":
                                customer = new Customer(UUID.randomUUID().toString(), title, fname, username, password, email, hometown);
                                return customer;
                            case "n":
                                System.out.println("Please sign up again");
                                break confirmExit;
                            default:
                                System.out.println("\nResponse not recognized");
                        }
                    }
                }
            }
        }

    }
//        private Customer updateInfo(String title, String fname, String username, String password, String email, String hometown) {
//            Customer customer;
//            Scanner scan = new Scanner(System.in);
//
//            System.out.println("What would you like to change? ");
//            System.out.println("1. Title \n2. Name \n3. username \n4. password \n5. email \n6. hometown");
//
//            switch (scan.nextLine()) {
//                case "1":
//                    while (true) {
//                        System.out.println("Please enter your title: ");
//                        title = scan.nextLine();
//
//                        try {
//                            userService.isValidTitle(title);
//                            break;
//                        } catch (InvalidUserException e) {
//                            System.out.println(e.getMessage());
//                        }
//                    }
//                default:
//                    System.out.println("\nResponse not recognized");
//            }
//        }
}
