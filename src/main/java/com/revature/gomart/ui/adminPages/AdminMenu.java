package com.revature.gomart.ui.adminPages;

import com.revature.gomart.daos.*;
import com.revature.gomart.ui.PageServices;
import com.revature.gomart.models.*;
import com.revature.gomart.services.*;
import com.revature.gomart.ui.LoginMenu;
import com.revature.gomart.ui.MenuIF;
import dnl.utils.text.table.TextTable;

import java.util.List;
import java.util.Scanner;

public class AdminMenu extends PageServices implements MenuIF {

    private final User user;

    public AdminMenu(User user, UserService userService, ProductService productService, OrderService orderService, OPService opService, AddressService addressService) {
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
                System.out.println("\nWelcome in, " + user.getFname());
                System.out.println("What would you like to do?: ");
                System.out.println(
                          "1. View warehouses      2. View users " +
                        "\n3. View all orders      4. Sign out " +
                        "\n5. Exit the Pokemart");

                choiceExit:
                {
                    switch (scan.nextLine()) {
                        case "1":
                            new AdminWarehouseMenu(user, userService, productService, orderService, opService, addressService, new WarehouseService(new WarehouseDAO())).start();
                            break exit;
                        case "2":
                            new AdminUserViewMenu(user, userService, productService, orderService, opService, addressService).start();
                            break exit;
                        case "3":
                            TextTable allOrders = getAllOrders();
                            allOrders.printTable();
                            System.out.println("\nPress any key to continue");
                            scan.nextLine();
                            break choiceExit;
                        case "4":
                            System.out.println("\nThank you!");
                            new LoginMenu(userService, productService, orderService, opService, addressService).start();
                            break exit;
                        case "5":
                            System.out.println("\nThank you! \nWe hope to see you again!");
                            break exit;
                        default:
                            System.out.println("Input not recognized.");
                    }
                }
            }
        }
    }

    public TextTable getAllOrders() {
        List<Order> orders = orderService.getAll();
        String[] tableHeaders = {"Order ID  ", "Order Date  ","Delivery type  ", "Delivery date  ", "User  "};
        Object[][] tableData = new Object[orders.size()][5];

        for (int i = 0; i < orders.size(); i++) {
            for (int j = 0; j < 5; j++) {
                if (j == 0) {
                    tableData[i][j] = orders.get(i).getId() + "  ";
                } if (j == 1) {
                    tableData[i][j] = orders.get(i).getOrderDate() + "  ";
                } if (j == 2) {
                    tableData[i][j] = orders.get(i).getDeliveryType() + "  ";
                } if (j == 3) {
                    tableData[i][j] = orders.get(i).getDeliveryDate() + "  ";
                } if (j == 4) {
                    tableData[i][j] = userService.findUsernameById(orders.get(i).getUser_id()) + "  ";
                }
            }
        }

        TextTable table = new TextTable(tableHeaders, tableData);
        table.setAddRowNumbering(false);

        return table;
    }
}
