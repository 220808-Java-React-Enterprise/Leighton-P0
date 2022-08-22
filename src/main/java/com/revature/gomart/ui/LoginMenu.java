package com.revature.gomart.ui;

import com.revature.gomart.models.*;
import com.revature.gomart.services.*;
import com.revature.gomart.ui.adminPages.AdminLogin;
import com.revature.gomart.utils.custom_exceptions.*;

import java.util.Scanner;
import java.util.UUID;

public class LoginMenu extends PageServices implements MenuIF {

    public LoginMenu(UserService userService, ProductService productService, OrderService orderService, OPService opService, AddressService addressService) {
        super(userService, productService, orderService, opService, addressService);
    }

    @Override
    public void start() {
        Scanner scan = new Scanner(System.in);

        exit:
        {
            while (true) {
                System.out.println("\nWelcome to the Pokemart");
                System.out.println("Press one of the following keys to continue:");
                System.out.println("1. Login to an existing account");
                System.out.println("2. Create a new account");
                System.out.println("3. Exit the Pokemart");

                System.out.println("\nEnter:");

                switch (scan.nextLine()) {
                    case "1":
                        login();
                        break exit;
                    case "2":
                        Customer customer = signup();
                        Order order = new Order(customer.getId());
                        userService.register(customer);
                        orderService.createNew(order);
                        new LandingPage(customer, userService, productService, orderService, opService, addressService).start();
                        break exit;
                    case "3":
                        System.out.println("We hope to see you again!");
                        break exit;
                    case "+":
                        new AdminLogin(userService, productService, orderService, opService, addressService).start();
                        break exit;
                    default:
                        System.out.println("\nSorry, I didn't catch that\nPlease select either 1, 2, or 3");
                }
            }
        }
    }

    public void login() {
        String username = "";
        String password = "";
        Scanner scan = new Scanner(System.in);

        System.out.println("Signing in existing user");

        exit:
        {
            while (true) {
                System.out.println("\nPlease enter your username: ");
                username = scan.nextLine();

                System.out.println("\nPlease enter your password: ");
                password = scan.nextLine();

                try {
                    User user = userService.login(username, password);

                    Order order = orderService.retrieve(false, user.getId());
                    if (order == null) {
                        System.out.println("Creating a cart for you");
                        Order o = new Order(user.getId());
                        orderService.createNew(o);
                    }

                    new LandingPage(user, userService, productService, orderService, opService, addressService).start();


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
                            new LandingPage(customer, userService, productService, orderService, opService, addressService).start();
                            break exit;
                        default:
                            System.out.println("Input not recognized");
                    }
                }
            }
        }
    }

    public Customer signup() {
        String title = "";
        String fname = "";
        String username = "";
        String password = "";
        String password2 = "";
        String email = "";
        String hometown = "";

        Customer customer;
        Scanner scan = new Scanner(System.in);

        System.out.println("Create your new account! \n");

        exit:
        {
            while (true) {
                titleExit:
                {
                    while (true) {
                        System.out.println("\nPlease enter your title: ");
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
                        System.out.println("\nPlease enter your name: ");
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
                        System.out.println("\nPlease create a username (3-15 characters): ");
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
                            System.out.println("\nPlease create a password (Must contain: 1 uppercase letter, 1 lowercase letter, 1 number, one special character, 8-20 characters): ");
                            password = scan.nextLine();

                            userService.isValidPassword(password);

                            System.out.println("\nReenter your new password: ");
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
                        System.out.println("\nPlease enter your email: ");
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
                        System.out.println("\nPlease enter your hometown: ");
                        hometown = scan.nextLine();

                        try {
                            userService.isValidKantoTown(hometown);
                            break hometownExit;
                        } catch (InvalidUserException e) {
                            System.out.println(e.getMessage());
                        }
                    }
                }
                System.out.println("\nPlease review the following information: ");
                System.out.println("\nYou are " + title + " " + fname);
                System.out.println("Username: " + username + "\nPassword: " + password + "\nEmail: " + email + "\nAnd you are from " + hometown);
                System.out.println("\nIs this correct? (y/n):");

                switch (scan.nextLine().toLowerCase()) {
                    case "y":
                        customer = new Customer(UUID.randomUUID().toString(), title, fname, username, password, email, hometown);
                        return customer;
                    case "n":
                        customer = new Customer(UUID.randomUUID().toString(), title, fname, username, password, email, hometown);
                        return updateInfo(customer);
                    default:
                        System.out.println("\nResponse not recognized");
                }
            }
        }
    }

    private Customer updateInfo(Customer customer) {
        Scanner scan = new Scanner(System.in);

        exit:
        {
            while (true) {
                System.out.println("What would you like to change? ");
                System.out.println("1. Title: " + customer.getTitle() +
                        "\n2. Name: " + customer.getFname() +
                        "\n3. Username: " + customer.getUsername() +
                        "\n4. Password: " + customer.getPassword() +
                        "\n5. Email: " + customer.getEmail() +
                        "\n6. Hometown: " + customer.getHometown() +
                        "\nx. Go back");
                String input = scan.nextLine();

                choiceExit:
                {
                    while (true) {
                        switch (input) {
                            case "1":
                                while (true) {
                                    System.out.println("\nPlease enter your title: ");
                                    String title = scan.nextLine();

                                    try {
                                        userService.isValidTitle(title);
                                        customer.setTitle(title);
                                        break choiceExit;
                                    } catch (InvalidUserException e) {
                                        System.out.println(e.getMessage());
                                    }
                                }
                            case "2":
                                while (true) {
                                    System.out.println("\nPlease enter your name: ");
                                    String fname = scan.nextLine();

                                    try {
                                        userService.isValidName(fname);
                                        customer.setFname(fname);
                                        break choiceExit;
                                    } catch (InvalidUserException e) {
                                        System.out.println(e.getMessage());
                                    }
                                }
                            case "3":
                                while (true) {
                                    System.out.println("\nPlease create a username (3-15 characters): ");
                                    String username = scan.nextLine();

                                    try {
                                        userService.isValidUsername(username);
                                        userService.isDuplicateUsername(username);
                                        customer.setUsername(username);
                                        break choiceExit;
                                    } catch (InvalidUserException e) {
                                        System.out.println(e.getMessage());
                                    }
                                }
                            case "4":
                                while (true) {
                                    try {
                                        System.out.println("\nPlease create a password (Must contain: 1 uppercase letter, 1 lowercase letter, 1 number, one special character, 8-20 characters): ");
                                        String password = scan.nextLine();

                                        userService.isValidPassword(password);

                                        System.out.println("\nReenter your new password: ");
                                        String password2 = scan.nextLine();

                                        userService.isSamePassword(password2, password);

                                        customer.setUsername(password);
                                        break choiceExit;
                                    } catch (InvalidUserException e) {
                                        System.out.println(e.getMessage());
                                    }
                                }
                            case "5":
                                while (true) {
                                    System.out.println("\nPlease enter your email: ");
                                    String email = scan.nextLine();

                                    try {
                                        userService.isValidEmail(email);
                                        userService.isDuplicateEmail(email);

                                        customer.setEmail(email);
                                        break choiceExit;
                                    } catch (InvalidUserException e) {
                                        System.out.println(e.getMessage());
                                    }
                                }
                            case "6":
                                while (true) {
                                    System.out.println("\nPlease enter your hometown: ");
                                    String hometown = scan.nextLine();

                                    try {
                                        userService.isValidKantoTown(hometown);

                                        customer.setHometown(hometown);
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
        return customer;
    }
}
