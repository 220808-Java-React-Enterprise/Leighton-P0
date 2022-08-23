package com.revature.gomart.ui.adminPages;

import com.revature.gomart.ui.*;
import com.revature.gomart.models.*;
import com.revature.gomart.services.*;
import dnl.utils.text.table.TextTable;

import java.util.List;
import java.util.Scanner;

public class AdminUserViewMenu extends PageServices implements MenuIF {

    private final User user;

    public AdminUserViewMenu(User user, UserService userService, ProductService productService, OrderService orderService, OPService opService, AddressService addressService) {
        super(userService, productService, orderService, opService, addressService);
        this.user = user;
    }

    @Override
    public void start() {
        System.out.println("\n--------------------------------------------------------------------------------------\n");
        Scanner scan = new Scanner(System.in);

        exit:
        {
            while (true) {
                System.out.println("Welcome to the user database \nWhat would you like to do?");
                System.out.println("\n1. View all users \n2. Search for users by name \n3. Go back to menu");
                choiceExit:
                {
                    switch(scan.nextLine()) {
                        case "1":
                            System.out.println("\nRetrieving users from database...");
                            List<User> users = userService.getAllUsers();
                            TextTable userTable = printUsers(users);
                            userTable.printTable();
                            break choiceExit;
                        case "2":
                            System.out.println("\nSearching for user...");
                            User foundUser = userService.findByUsername(scan.nextLine());
                            if (foundUser != null) {
                                System.out.println("\nUser found! \n");
                                System.out.println(foundUser.getTitle() + " " + foundUser.getFname() + "\nID: " + foundUser.getId() + "\nUsername: " + foundUser.getUsername() + "\nEmail: " + foundUser.getEmail() + "\nHometown: " + foundUser.getHometown());

                                List<Order> userOrders = orderService.adminGetByUserId(foundUser.getId());
                                TextTable orderTable = printUserOrders(userOrders);

                                System.out.println(foundUser.getFname() + "'s order history:");
                                orderTable.printTable();

                            } else {
                                System.out.println("User not found");
                            }
                            break choiceExit;
                        case "3":
                            new AdminMenu(user, userService, productService, orderService, opService, addressService).start();
                            break exit;
                    }
                }
            }
        }
    }

    public TextTable printUsers(List<User> users) {

        String[] tableHeaders = {"User ", "Username", "Email", "Hometown"};
        Object[][] tableData = new Object[users.size()][4];

        for (int i = 0; i < users.size(); i++) {
            for (int j = 0; j < 4; j++) {
                if (j == 0) {
                    tableData[i][j] = users.get(i).getTitle() + "  " + users.get(i).getFname();
                } if (j == 1) {
                    tableData[i][j] = users.get(i).getUsername() + "  ";
                } if (j == 2) {
                    tableData[i][j] = users.get(i).getEmail() + "  ";
                } if (j == 3) {
                    tableData[i][j] = users.get(i).getHometown() + "  ";
                }
            }
        }

        TextTable table = new TextTable(tableHeaders, tableData);
        table.setAddRowNumbering(true);

        return table;
    }

    public TextTable printUserOrders(List<Order> orders) {

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
