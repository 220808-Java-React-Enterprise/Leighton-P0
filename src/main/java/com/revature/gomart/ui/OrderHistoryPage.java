package com.revature.gomart.ui;

import com.revature.gomart.models.*;
import com.revature.gomart.services.*;
import com.revature.gomart.utils.custom_exceptions.InvalidUserException;
import dnl.utils.text.table.TextTable;

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
        List<Order> pastOrders = orderService.getPastOrders(user.getId());

        exit:
        {
            while (true) {
                if (pastOrders == null) {
                    System.out.println("You have no past orders \n \n1. to return to your profile \n2. to return to the store page");
                    String userChoice = scan.nextLine();
                        switch (userChoice) {
                            case "1":
                                new UserProfile(user, userService, productService, orderService, opService, addressService).start();
                                break exit;
                            case "2":
                                new LandingPage(user, userService, productService, orderService, opService, addressService).start();
                                break exit;
                    }
                } else {
                    TextTable table = printNewTable(pastOrders);
                    table.printTable();
                }
            }
        }
    }

    public TextTable printNewTable(List<Order> orders) {

        String[] tableHeaders = {"Order date", "Total cost", "Delivery Type", "Delivery Date"};
        Object[][] tableData = new Object[orders.size()][4];

        for (int i = 0; i < orders.size(); i++) {
            for (int j = 0; j < 2; j++) {
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
