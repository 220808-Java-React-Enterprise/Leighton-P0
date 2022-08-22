package com.revature.gomart.ui;
import com.revature.gomart.models.*;
import com.revature.gomart.services.*;
import dnl.utils.text.table.TextTable;

import java.util.List;
import java.util.Scanner;

public class OrderPage extends PageServices implements MenuIF{

        private final User user;
        public OrderPage(User user, UserService userService, ProductService productService, OrderService orderService, OPService opService, AddressService addressService) {
            super(userService, productService, orderService, opService, addressService);
            this.user = user;
        }

    @Override
    public void start() {
        Scanner scan = new Scanner(System.in);
        exit:
        {
            while (true) {
                Order currentOrder = orderService.retrieve(false, user.getId());
                List<OrderProduct> products = opService.getOrderProducts((currentOrder.getId()));

                if (products.size() != 0) {
                    System.out.println("Your cart: \n");
                    TextTable table = generateOrder(products, currentOrder);
                    currentOrder = orderService.retrieve(false, user.getId());
                    table.setAddRowNumbering(false);

                    table.printTable();
                    System.out.println("\nTotal cost: " + orderService.getCost(currentOrder) + "\n");
                    System.out.println("\nWhat would you like to do? \n1. Check out \n2. View Profile \n3. Back to store page");

                    switch (scan.nextLine()) {
                        case "1":
                            new CheckoutPage(user, userService, productService, orderService, opService, addressService).start();
                            break exit;
                        case "2":
                            new UserProfile(user, userService, productService, orderService, opService, addressService).start();
                            break exit;
                        case "3":
                            new LandingPage(user, userService, productService, orderService, opService, addressService).start();
                            break exit;
                        default:
                            System.out.println("Input not recognized");
                    }
                } else {
                    System.out.println("\nYour cart is empty!");
                    System.out.println("\nWhat would you like to do? \n1. View Profile \n2. Back to store page");
                    switch (scan.nextLine()) {
                        case "1":
                            new UserProfile(user, userService, productService, orderService, opService, addressService).start();
                            break exit;
                        case "2":
                            new LandingPage(user, userService, productService, orderService, opService, addressService).start();
                            break exit;
                        default:
                            System.out.println("Input not recognized");
                    }
                }
            }
        }
    }

    public TextTable generateOrder(List<OrderProduct> products, Order order) {
        String[] tableHeaders = {"Item", "No.  ","Price "};
        Object[][] tableData = new Object[products.size()][3];

        int total_cost = 0;

        for (int i = 0; i < products.size(); i++) {
            String productName = products.get(i).getProduct_name();
            int productQuantity = products.get(i).getProduct_quantity();
            int productPrice = products.get(i).getProduct_price();
            int productTot = productQuantity * productPrice;

            for (int j = 0; j < 3; j++) {
                if (j == 0) {
                    tableData[i][j] = productName + "  ";
                } if (j == 1) {
                    tableData[i][j] = productQuantity + "  ";
                } if (j == 2) {
                    tableData[i][j] = productPrice + "  ";
                }
            }
            total_cost += productTot;
        }

        orderService.updateCost(order, total_cost);
        TextTable table = new TextTable(tableHeaders, tableData);
        table.setAddRowNumbering(false);

        return table;
    }
}
