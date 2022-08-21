package com.revature.gomart.ui;

import com.revature.gomart.models.*;
import com.revature.gomart.services.*;
import com.revature.gomart.daos.*;
import dnl.utils.text.table.TextTable;
import org.apache.commons.lang.StringUtils;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class LandingPage implements MenuIF {

    private final User user;

    private final UserService userService;
    private final ProductService productService;
    private final OrderService orderService;
    private final OPService opService;

    public LandingPage(User user, UserService userService, ProductService productService, OrderService orderService, OPService opService) {
        this.user = user;
        this.userService = userService;
        this.productService = productService;
        this.orderService = orderService;
        this.opService = opService;
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

                String productChoice = scan.nextLine();
                choiceExit:
                {
                    while (true) {
                        switch (productChoice) {
                            case "1":
                                displayPotions();
                                break choiceExit;
                            case "2":
                                displayMeds();

                            case "3":
                                displayPokeballs();

                            case "4":
                                displayFieldItems();

                            case "5":
                                new OrderPage(user, userService, productService).start();
                                break exit;
                            case "6":
                                new UserProfile(user, userService).start();
                                break exit;
                            case "7":
                                System.out.println("We hope to see you again");
                                break exit;
                            default:
                                System.out.println("Input not recognized.");
                        }
                    }
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
        String userChoice = scan.nextLine();

        switch (userChoice) {
            case "1":
                potionExit:
                {
                    while (true) {
                        System.out.println("\nHow many would you like to buy?");
                        String quantityChoice = scan.nextLine();
                        try {
                            int input = Integer.parseInt(quantityChoice);
                            Order currentOrder = orderService.retrieve(false, user.getId());
                            Product currentProduct = productService.getProductbyId("M001");

                            if (input > currentProduct.getStock()) {
                                System.out.println("We apologize, but we currently do not have that many items in stock.");
                            } else if (opService.checkForProduct(currentOrder, currentProduct) != null) {
                                OrderProduct existingOP = opService.checkForProduct(currentOrder, currentProduct);
                                opService.addToProduct(existingOP, currentProduct, input);
                                productService.reduceProductStock(currentProduct, input);
                                break potionExit;
                            } else {
                                opService.saveToOP(currentOrder, currentProduct, input);
                                productService.reduceProductStock(currentProduct, input);
                                break potionExit;
                            }
                        } catch (NumberFormatException e) {
                            System.out.println("please input a number");
                        }
                    }
                }
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
