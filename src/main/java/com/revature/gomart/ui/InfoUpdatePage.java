package com.revature.gomart.ui;

import com.revature.gomart.models.Customer;
import com.revature.gomart.services.UserService;
import com.revature.gomart.utils.custom_exceptions.InvalidUserException;

import java.util.Scanner;

public class InfoUpdatePage implements MenuIF{

    private final UserService userService;

    public InfoUpdatePage(UserService userService) {this.userService = userService;}

    @Override
    public void start() {

    }
//    private Customer updateInfo(String title, String fname, String username, String password, String email, String hometown) {
//        Customer customer;
//        Scanner scan = new Scanner(System.in);
//
//        System.out.println("What would you like to change? ");
//        System.out.println("1. Title \n2. Name \n3. username \n4. password \n5. email \n6. hometown");
//
//        switch (scan.nextLine()) {
//            case "1":
//                while (true) {
//                    System.out.println("Please enter your title: ");
//                    title = scan.nextLine();
//
//                    try {
//                        userService.isValidTitle(title);
//                        break;
//                    } catch (InvalidUserException e) {
//                        System.out.println(e.getMessage());
//                    }
//                }
//            default:
//                System.out.println("\nResponse not recognized");
//        }
//    }
}
