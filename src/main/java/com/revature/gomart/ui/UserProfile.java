package com.revature.gomart.ui;

import com.revature.gomart.models.*;
import com.revature.gomart.services.*;
import com.revature.gomart.utils.custom_exceptions.InvalidUserException;

import java.util.Scanner;

public class UserProfile extends PageServices implements MenuIF{

    private final User user;

    public UserProfile(User user, UserService userService, ProductService productService, OrderService orderService, OPService opService, AddressService addressService) {
        super(userService, productService, orderService, opService, addressService);
        this.user = user;
    }

    @Override
    public void start() {
        Scanner scan = new Scanner(System.in);
        exit:
        {
            while (true) {
                System.out.println("Welcome to your profile! Here is your current information: \n");

                User current = userService.findById(user.getId());
                System.out.println("Title: " + current.getTitle() +
                        "\nName: " + current.getFname() +
                        "\nUsername: " + current.getUsername() +
//                        "\nPassword: " + current.getPassword() +
                        "\nEmail: " + current.getEmail() +
                        "\nHometown: " + current.getHometown() + "\n");
                System.out.println("\nWhat would you like to do? \n1. View cart \n2. View past orders \n3. View address book \n4. Edit info \nx. Go back to main menu \n");
                String userChoice = scan.nextLine();

                choiceExit:
                {
                    while (true) {
                        switch (userChoice) {
                            case "1":
                                new OrderPage(user, userService, productService, orderService, opService, addressService).start();
                                break exit;
                            case "2":
                                new OrderHistoryPage(user, userService, productService, orderService, opService, addressService).start();
                                break exit;
                            case "3" :
                                new AddressPage(user, userService, productService, orderService, opService, addressService).start();
                                break exit;
                            case "4":
                                User updatedUser = updateInfo(user);
                                userService.updateUser(updatedUser);
                                break choiceExit;
                            case "x":
                                new LandingPage(user, userService, productService, orderService, opService, addressService).start();
                                break exit;
                        }
                    }
                }
            }
        }
    }
    private User updateInfo(User customer) {
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
