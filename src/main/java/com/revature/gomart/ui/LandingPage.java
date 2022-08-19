package com.revature.gomart.ui;

import com.revature.gomart.models.*;
import com.revature.gomart.services.ProductService;
import com.revature.gomart.services.UserService;
import com.revature.gomart.daos.*;
import dnl.utils.text.table.TextTable;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class LandingPage implements MenuIF {

    private final User user;

    private final UserService userService;
    private final ProductService productService;

    public LandingPage(User user, UserService userService, ProductService productService) {
        this.user = user;
        this.userService = userService;
        this.productService = productService;
    }

    @Override
    public void start() {
        Scanner scan = new Scanner(System.in);

        exit:
        {
            while (true) {
                System.out.println("\nWelcome back, " + user.getTitle() + " " + user.getFname() + "\nWe're still getting things set up, please be patient as we prepare a great experience for you!");

                System.out.println("What would you like to do?: ");
                System.out.println("1. View potions         2. View medicines \n3. View Pokeballs       4. View field items \n5. View cart            6. View profile \n7. Sign out");


                switch (scan.nextLine()) {
                    case "1":
                        displayPotions();
                        break exit;
                    case "2":
                        displayMeds();
                        break exit;
                    case "3":
                        displayPokeballs();
                        break exit;
                    case "4":
                        displayFieldItems();
                        break exit;
                    case "5":
                        new OrderPage(user, userService, productService).start();
                        break exit;
                    case "6":
                        new UserProfile(user, userService).start();
                        break exit;
                    case "7":
                        System.out.println("We hope to see you again");
                        break;
                    default:
                        System.out.println("Input not recognized.");
                }
            }
        }
    }

    private void displayPotions() {
        List<Product> products = productService.getProductsByCategory("P");
        Scanner scan = new Scanner(System.in);

        String[] tableHeaders = {"Item", "Price  "};
        Object[][] tableData = new Object[products.size()][2];

        for (int i = 0; i < products.size(); i++) {
            for (int j = 0; j < 2; j++) {
                if (j == 0) {
                    tableData[i][j] = products.get(i).getItemName() + "  ";
                } else {
                    tableData[i][j] = Integer.toString(products.get(i).getPrice());
                }
            }
        }

        TextTable t = new TextTable(tableHeaders, tableData);
        t.setAddRowNumbering(true);
        t.printTable();

        System.out.println("\nPlease select the item you would like to purchase\nPress x to go back at any time");

        switch (scan.nextLine()) {
            case "1":
                System.out.println("\nHow many would you like to buy?");

        }
    }
    private void displayMeds() {
        List<Product> products = productService.getProductsByCategory("M");

        for (int i = 0; i < products.size(); i++) {
            System.out.println((i+1) + ". " + products.get(i).getItemName());
        }
    }

    private void displayPokeballs() {
        List<Product> products = productService.getProductsByCategory("B");

        for (int i = 0; i < products.size(); i++) {
            System.out.println((i+1) + ". " + products.get(i).getItemName());
        }
    }

    private void displayFieldItems() {
        List<Product> products = productService.getProductsByCategory("F");

        for (int i = 0; i < products.size(); i++) {
            System.out.println((i+1) + ". " + products.get(i).getItemName());
        }
    }
}
