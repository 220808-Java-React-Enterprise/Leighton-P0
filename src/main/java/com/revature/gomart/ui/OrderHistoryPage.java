package com.revature.gomart.ui;

import com.revature.gomart.models.*;
import com.revature.gomart.services.*;
import dnl.utils.text.table.TextTable;
import org.apache.commons.lang.math.NumberUtils;

import java.util.List;
import java.util.Scanner;

public class OrderHistoryPage extends PageServices implements MenuIF {

    private final User user;

    public OrderHistoryPage(User user, UserService userService, ProductService productService, OrderService orderService, OPService opService, AddressService addressService) {
        super(userService, productService, orderService, opService, addressService);
        this.user = user;
    }

    @Override
    public void start() {
        Scanner scan = new Scanner(System.in);
        List<Order> userOrders = orderService.getByUserId(user.getId());

        exit:
        {
            while (true) {
                if (userOrders.size() <= 1) {
                    System.out.println("You have no past orders \n \n1. Return to your profile \n2. Return to the store page");
                    String input = scan.nextLine();
                    switch (input) {
                        case "1":
                            new UserProfile(user, userService, productService, orderService, opService, addressService).start();
                            break exit;
                        case "2":
                            new LandingPage(user, userService, productService, orderService, opService, addressService).start();
                            break exit;
                    }
                } else {

                    List<Order> pastOrders = determineListOrder(orderService.getPastOrdersDescending(user.getId()));


                    System.out.println(pastOrders.size());
                    TextTable table = printPastOrder(pastOrders);
                    table.printTable();
                    System.out.println("\nWhat would you like to do?");
                    System.out.println("\n1. Get order details \n2. Back to my Profile \n3. Back to the store page");
                    switch (scan.nextLine()) {
                        case "1": {
                            while (true) {
                                System.out.println("\nSelect the order you would like to view:");
                                String choice = scan.nextLine();
                                if (NumberUtils.isNumber(choice)) {
                                    int orderChoice = Integer.parseInt(choice);
                                    if (orderChoice <= pastOrders.size()) {
                                        Order order = pastOrders.get(orderChoice - 1);
                                        List<OrderProduct> products = opService.getOrderProducts(order.getId());

                                        TextTable orderTable = new OrderPage(user, userService, productService, orderService, opService, addressService).generateOrder(products, order);
                                        orderTable.printTable();
                                        System.out.println("\nWhat would you like to do? \n\n1. Back to order history \n2. Back to my profile \n3. Back to store page");
                                        switch(scan.nextLine()) {
                                            case "1":
                                                new OrderHistoryPage(user, userService, productService, orderService, opService, addressService).start();
                                                break exit;
                                            case "2":
                                                new UserProfile(user, userService, productService, orderService, opService, addressService).start();
                                                break exit;
                                            case "3":
                                                new LandingPage(user, userService, productService, orderService, opService, addressService).start();
                                                break exit;
                                            default:
                                                System.out.println("Input not recognized.");
                                        }
                                    } else {
                                        System.out.println("Order not found");
                                    }
                                } else {
                                    System.out.println("Input not recognized");
                                }
                            }
                        }
                        case "2":
                            new UserProfile(user, userService, productService, orderService, opService, addressService).start();
                            break exit;
                        case "3":
                            new LandingPage(user, userService, productService, orderService, opService, addressService).start();
                            break exit;
                        default:
                            System.out.println("Input not recognized.");
                    }
                }
            }
        }
    }

    public List<Order> determineListOrder(List<Order> pastOrders) {
        Scanner scan = new Scanner(System.in);
        System.out.println("\nHow would you like the table sorted?");
        System.out.println("\n1. Date (newest to oldest) \n2. Date (oldest to newest) \n3. Price (highest to lowest) \n4. Price (lowest to highest) \n");
        String userInput = scan.nextLine();
        while (true) {
            switch (userInput) {
                case "1":
                    pastOrders = orderService.getPastOrdersDescending(user.getId());
                    return pastOrders;
                case "2":
                    pastOrders = orderService.getPastOrdersAscending(user.getId());
                    return pastOrders;
                case "3":
                    pastOrders = orderService.getPastOrdersPriceDescending(user.getId());
                    return pastOrders;
                case "4":
                    pastOrders = orderService.getPastOrdersPriceAscending(user.getId());
                    return pastOrders;
                default:
                    System.out.println("Input not recognized");
            }
        }
    }

    public TextTable printPastOrder(List<Order> orders) {

        String[] tableHeaders = {"Order date", "Total cost", "Delivery Type", "Delivery Date"};
        Object[][] tableData = new Object[orders.size()][4];

        for (int i = 0; i < orders.size(); i++) {
            for (int j = 0; j < 4; j++) {
                if (j == 0) {
                    tableData[i][j] = orders.get(i).getOrderDate() + "  ";
                } if (j == 1) {
                    tableData[i][j] = orders.get(i).getTot_price() + "  ";
                } if (j == 2) {
                    tableData[i][j] = orders.get(i).getDeliveryType() + "  ";
                } if (j == 3) {
                    tableData[i][j] = orders.get(i).getDeliveryDate() + "  ";
                }
            }
        }

        TextTable table = new TextTable(tableHeaders, tableData);
        table.setAddRowNumbering(true);

        return table;
    }
}
