package com.revature.gomart.ui;

import com.revature.gomart.models.*;
import com.revature.gomart.services.*;
import com.revature.gomart.daos.*;
import dnl.utils.text.table.TextTable;

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
                                break choiceExit;
                            case "3":
                                displayPokeballs();
                                break choiceExit;
                            case "4":
                                displayFieldItems();
                                break choiceExit;
                            case "5":
                                new OrderPage(user, userService, productService, orderService, opService).start();
                                break exit;
                            case "6":
                                new UserProfile(user, userService).start();
                                break exit;
                            case "7":
                                System.out.println("We hope to see you again");
                                break exit;
                            default:
                                System.out.println("Input not recognized.");
                                break exit;
                        }
                    }
                }
            }
        }
    }

    private void displayPotions() {
        List<Product> products = productService.getProductsByCategory("P");
        Scanner scan = new Scanner(System.in);

        TextTable itemTable = printNewTable(products);

        exit:
        {
            while (true) {
                itemTable.printTable();

                System.out.println("\nPlease select the item you would like to purchase\nPress x to go back at any time");
                String userChoice = scan.nextLine();



                potionsExit:
                {
                    while (true) {
                        switch (userChoice) {
                            case "1":
                                chooseProduct("M001");
                                break potionsExit;
                            case "2":
                                chooseProduct("M002");
                                break potionsExit;
                            case "3":
                                chooseProduct("M003");
                                break potionsExit;
                            case "4":
                                chooseProduct("M004");
                                break potionsExit;
                            case "5":
                                chooseProduct("M011");
                                break potionsExit;
                            case "x":
                                break exit;
                            default:
                                System.out.println("Input not recognized");
                                break potionsExit;
                        }
                    }
                }
            }
        }
    }
    private void displayMeds() {
        List<Product> products = productService.getProductsByCategory("M");
        Scanner scan = new Scanner(System.in);

        TextTable itemTable = printNewTable(products);

        exit:
        {
            while (true) {
                itemTable.printTable();

                System.out.println("\nPlease select the item you would like to purchase\nPress x to go back at any time");
                String userChoice = scan.nextLine();



                medicinesExit:
                {
                    while (true) {
                        switch (userChoice) {
                            case "1":
                                chooseProduct("M010");
                                break medicinesExit;
                            case "2":
                                chooseProduct("M020");
                                break medicinesExit;
                            case "3":
                                chooseProduct("M030");
                                break medicinesExit;
                            case "4":
                                chooseProduct("M040");
                                break medicinesExit;
                            case "5":
                                chooseProduct("M050");
                                break medicinesExit;
                            case "6":
                                chooseProduct("M110");
                                break medicinesExit;
                            case "7":
                                chooseProduct("M100");
                                break medicinesExit;
                            case "x":
                                break exit;
                            default:
                                System.out.println("Input not recognized");
                                break medicinesExit;
                        }
                    }
                }
            }
        }
    }

    private void displayPokeballs() {
        List<Product> products = productService.getProductsByCategory("B");
        Scanner scan = new Scanner(System.in);

        TextTable itemTable = printNewTable(products);

        exit:
        {
            while (true) {
                itemTable.printTable();

                System.out.println("\nPlease select the item you would like to purchase\nPress x to go back at any time");
                String userChoice = scan.nextLine();



                pokeballsExit:
                {
                    while (true) {
                        switch (userChoice) {
                            case "1":
                                chooseProduct("B001");
                                break pokeballsExit;
                            case "2":
                                chooseProduct("B010");
                                break pokeballsExit;
                            case "3":
                                chooseProduct("B100");
                                break pokeballsExit;
                            case "x":
                                break exit;
                            default:
                                System.out.println("Input not recognized");
                                break pokeballsExit;
                        }
                    }
                }
            }
        }
    }

    private void displayFieldItems() {
        List<Product> products = productService.getProductsByCategory("F");
        Scanner scan = new Scanner(System.in);

        TextTable itemTable = printNewTable(products);

        exit:
        {
            while (true) {
                itemTable.printTable();

                System.out.println("\nPlease select the item you would like to purchase\nPress x to go back at any time");
                String userChoice = scan.nextLine();



                fieldItemsExit:
                {
                    while (true) {
                        switch (userChoice) {
                            case "1":
                                chooseProduct("F001");
                                break fieldItemsExit;
                            case "2":
                                chooseProduct("F002");
                                break fieldItemsExit;
                            case "3":
                                chooseProduct("F003");
                                break fieldItemsExit;
                            case "4" :
                                chooseProduct("F111");
                            case "x":
                                break exit;
                            default:
                                System.out.println("Input not recognized");
                                break fieldItemsExit;
                        }
                    }
                }
            }
        }
    }

    public TextTable printNewTable(List<Product> products) {

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

        TextTable table = new TextTable(tableHeaders, tableData);
        table.setAddRowNumbering(true);

        return table;
    }

    public void chooseProduct(String id) {
        Scanner scan = new Scanner(System.in);
        System.out.println("\nHow many would you like to buy?");
        String quantityChoice = scan.nextLine();
        try {
            int input = Integer.parseInt(quantityChoice);
            Order currentOrder = orderService.retrieve(false, user.getId());
            Product currentProduct = productService.getProductbyId(id);
            OrderProduct existingOP = opService.checkForProduct(currentOrder, currentProduct);

            if (input > currentProduct.getStock()) {
                System.out.println("We apologize, but we currently do not have that many items in stock.");
            } else if (opService.checkForProduct(currentOrder, currentProduct) != null) {
                opService.addToProduct(existingOP, currentProduct, input);
                productService.reduceProductStock(currentProduct, input);
            } else {
                opService.saveToOP(currentOrder, currentProduct, input);
                productService.reduceProductStock(currentProduct, input);
            }
        } catch (NumberFormatException e) {
            System.out.println("please input a number");
        }
    }
}
