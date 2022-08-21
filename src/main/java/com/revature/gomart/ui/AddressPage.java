package com.revature.gomart.ui;

import com.revature.gomart.models.*;
import com.revature.gomart.services.*;
import com.revature.gomart.utils.custom_exceptions.*;

import java.sql.SQLOutput;
import java.util.Scanner;

public class AddressPage extends PageServices implements MenuIF {

    private final User user;

    public AddressPage(User user, UserService userService, ProductService productService, OrderService orderService, OPService opService, AddressService addressService) {
        super(userService, productService, orderService, opService, addressService);
        this.user = user;
    }


    @Override
    public void start() {
        Scanner scan = new Scanner(System.in);
        Address userAddress = addressService.retrieve(user.getId());
//        System.out.println("We're working on it!");
//        new UserProfile(user, userService, productService, orderService, opService, addressService).start();
        exit:
        {
            while (true) {
                if (userAddress == null) {
                    System.out.println("Add your address");
                    userAddress = createNewAddress();
                    addressService.createNew(userAddress);
                } else {
                    System.out.println("Here's the Address we have on file for you: \n");
                    System.out.println(
                            "Name: " + userAddress.getFullName() + " \n" +
                            "Street: " + userAddress.getStreet() + " \n" +
                            "City: " + userAddress.getCity() + " \n" +
                            "Region: " + userAddress.getRegion() + " \n"
                    );

                    System.out.println("What would you like to do? \n");
                    System.out.println("1. Edit my address \n2. Return to my profile \n3 Exit to store menu");

                    choiceExit:
                    {
                        while (true) {
                            switch(scan.nextLine()) {
                                case "1":
                                    Address newAddress = editAddress(userAddress);
                                    addressService.updateAddress(newAddress);
                                    break choiceExit;
                                case "2":
                                    new UserProfile(user, userService, productService, orderService, opService, addressService).start();
                                    break exit;
                                case "3":
                                    new LandingPage(user, userService, productService, orderService, opService, addressService).start();
                                default:
                                    System.out.println("Input not recognized");
                            }
                        }
                    }
                }
            }
        }
    }

    public Address createNewAddress() {
        Address address = new Address();
        Scanner scan = new Scanner(System.in);

        System.out.println("Create your new address \n");

        exit:
        {
            while (true) {
                fullNameExit:
                {
                    while (true) {
                        System.out.println("\nName: ");
                        String name = scan.nextLine();

                        try{
                            userService.isValidName(name);
                            address.setFullName(name);
                            break fullNameExit;
                        } catch (InvalidUserException e) {
                            System.out.println(e.getMessage());
                        }
                    }
                }
                streetExit:
                {
                    while (true) {
                        System.out.println("\nStreet address: ");
                        String street = scan.nextLine();

                        try {
                            userService.isValidStreetAddress(street);
                            address.setStreet(street);
                            break streetExit;
                        } catch (InvalidUserException e) {
                            System.out.println(e.getMessage());
                        }
                    }
                }
                cityExit:
                {
                    while (true) {
                        System.out.println("\nCity: ");
                        String city = scan.nextLine();

                        try {
                            userService.isValidKantoTown(city);
                            address.setCity(city);
                            break cityExit;
                        } catch (InvalidUserException e) {
                            System.out.println(e.getMessage());
                        }
                    }
                }
                regionExit:
                {
                    while (true) {
                        System.out.println("Region");
                        String region = scan.nextLine();

                        if (region != "Kanto") {
                            System.out.println("We apologize, but our services are currently limited to the Kanto region \nWe're working with our regional partners elsewhere to expand our services");
                        } else {
                            address.setRegion(region);
                            break regionExit;
                        }
                    }
                }
                System.out.println("\nPlease review the following information: \n");
                System.out.println(
                    address.getFullName() + " \n" +
                    address.getStreet() + " \n" +
                    address.getCity() + " \n" +
                    address.getRegion() + " \n"
                );
                System.out.println("Is this correct? (y/n): ");

                switch (scan.nextLine().toLowerCase()) {
                    case "y":
                        return address;
                    case "n":
                        return editAddress(address);
                    default:
                        System.out.println("Response not recognized");
                }
            }
        }
    }

    public Address editAddress(Address address) {
        Scanner scan = new Scanner(System.in);

        exit:
        {
            while (true) {
                System.out.println("\nWhat would you like to change?");
                System.out.println("\n1. Name: " + address.getFullName() +
                        "\n2. Street Address: " + address.getFullName() +
                        "\n3. City: " + address.getFullName() +
                        "\nx. Go back");

                choiceExit:
                {
                    while (true) {
                        switch (scan.nextLine()) {
                            case "1":
                                System.out.println("\nName: ");
                                String name = scan.nextLine();

                                try{
                                    userService.isValidName(name);
                                    address.setFullName(name);
                                    break choiceExit;
                                } catch (InvalidUserException e) {
                                    System.out.println(e.getMessage());
                                }
                            case "2":
                                System.out.println("\nStreet address: ");
                                String street = scan.nextLine();

                                try {
                                    userService.isValidStreetAddress(street);
                                    address.setStreet(street);
                                    break choiceExit;
                                } catch (InvalidUserException e) {
                                    System.out.println(e.getMessage());
                                }
                            case "3":
                                System.out.println("\nCity: ");
                                String city = scan.nextLine();

                                try {
                                    userService.isValidKantoTown(city);
                                    address.setCity(city);
                                    break choiceExit;
                                } catch (InvalidUserException e) {
                                    System.out.println(e.getMessage());
                                }
                            case "x":
                                break exit;
                            default:
                                System.out.println("Response not recognized");
                        }
                    }
                }
            }
        }
        return address;
    }
}
