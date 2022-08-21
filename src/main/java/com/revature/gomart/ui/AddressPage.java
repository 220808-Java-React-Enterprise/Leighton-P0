package com.revature.gomart.ui;

import com.revature.gomart.models.*;
import com.revature.gomart.services.*;

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
                            userAddress.getFullName() + " \n" +
                            userAddress.getStreet1() + " \n" +
                            userAddress.getStreet2() + " \n" +
                            userAddress.getCity() + " \n" +
                            userAddress.getRegion() + " \n"
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

        return null;
    }

    public Address editAddress(Address address) {return null;}
}
