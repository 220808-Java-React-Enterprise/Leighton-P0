package com.revature.gomart.ui.adminPages;

import com.revature.gomart.daos.WarehouseDAO;
import com.revature.gomart.models.*;
import com.revature.gomart.services.*;
import com.revature.gomart.ui.MenuIF;
import com.revature.gomart.ui.PageServices;
import dnl.utils.text.table.TextTable;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AdminWarehouseMenu extends PageServices implements MenuIF {

    private final User user;

    private final WarehouseService whService;


    public AdminWarehouseMenu(User user, UserService userService, ProductService productService, OrderService orderService, OPService opService, AddressService addressService, WarehouseService whService) {
        super(userService, productService, orderService, opService, addressService);
        this.user = user;
        this.whService = whService;
    }

    @Override
    public void start() {
        System.out.println("\n--------------------------------------------------------------------------------------\n");
        Scanner scan = new Scanner(System.in);
        exit:
        {
            while (true) {
                System.out.println("\nWelcome to the warehouses! Please select which warehouse you'd like to view");
                System.out.println("\n1. Saffron City Warehouse \nStock: Medicines and Potions \n\n2. Vermillion City Warehouse \nStock: Pokeballs \n\n3. Celadon City Warehouse \nStock: Field items \n\n4. Main menu");

                choiceExit:
                {
                    switch (scan.nextLine()) {
                        case "1":
                            displayWarehouse("WH001");
                            break exit;
                        case "2":
                            displayWarehouse("WH002");
                            break exit;
                        case "3":
                            displayWarehouse("WH003");
                            break exit;
                        case "4":
                            new AdminMenu(user, userService, productService, orderService, opService, addressService).start();
                            break exit;
                        default:
                            System.out.println("Input not recognized");
                    }
                }
            }
        }
    }

    public void displayWarehouse(String id) {
        Scanner scan = new Scanner(System.in);
        Warehouse wh = whService.getById(id);
        exit:
        {
            while (true) {
                System.out.println("\nWelcome to the " + wh.getLocation() + " warehouse \nHere is our current stock: \n");

                TextTable table = displayProducts(id);
                table.printTable();

                System.out.println("\nWhat would you like to do?");
                System.out.println("1. View orders from this warehouse \n2. Restock items \n3. Warehouse menu");
                choiceExit:
                {
                    switch (scan.nextLine()) {
                        case "1":
                            TextTable orderTable = displayOrders(id);
                            orderTable.printTable();
                            System.out.println("Press any key to continue");
                            scan.nextLine();
                            break choiceExit;
                        case "2":
                            System.out.println("Press any key to confirm");
                            scan.nextLine();
                            restockItems(id);
                            break choiceExit;
                        case "3":
                            new AdminWarehouseMenu(user, userService, productService, orderService, opService, addressService, new WarehouseService(new WarehouseDAO())).start();
                            break exit;
                    }
                }
            }
        }
    }



    public TextTable displayProducts(String whid) {
        List<Product> products = productService.getByWarehouseId(whid);

        String[] tableHeaders = {"Item ID", "Item name", "Current stock  ", "Max Stock "};
        Object[][] tableData = new Object[products.size()][4];

        for (int i = 0; i < products.size(); i++) {
            for (int j = 0; j < 4; j++) {
                if (j == 0) {
                    tableData[i][j] = products.get(i).getId() + "  ";
                }
                if (j == 1) {
                    tableData[i][j] = products.get(i).getItemName() + "  ";
                }
                if (j == 2) {
                    tableData[i][j] = Integer.toString(products.get(i).getStock());
                }
                if (j == 3) {
                    tableData[i][j] = Integer.toString(products.get(i).getMaxStock());

                }
            }
        }

        TextTable table = new TextTable(tableHeaders, tableData);
        table.setAddRowNumbering(false);

        return table;
    }

    public TextTable displayOrders(String whid) {
        List<String> pids = productService.getIdByWarehouseId(whid);
        List<List<OrderProduct>> orderList = new ArrayList<>();
        List<OrderProduct> allOrders = new ArrayList<>();


        for (String id : pids) {
            orderList.add(opService.getProductOrders(id));
        }

        for (List<OrderProduct> l : orderList) {
            allOrders.addAll(l);
        }


        String[] tableHeaders = {"Order ID  ", "Order Date  ", "User  ", "Product id  ", "Product name  ", "Quantity  "};
        Object[][] tableData = new Object[allOrders.size()][6];

        for (int i = 0; i < allOrders.size(); i++) {
            for (int j = 0; j < 6; j++) {
                if (j == 0) {
                    tableData[i][j] = allOrders.get(i).getOrder_id() + "  ";
                }
                if (j == 1) {
                    tableData[i][j] = orderService.getOrderDateById(allOrders.get(i).getOrder_id()) + "  ";
                }
                if (j == 2) {
                    tableData[i][j] = userService.findUsernameById(orderService.getUserIdById(allOrders.get(i).getOrder_id())) + "  ";
                }
                if (j == 3) {
                    tableData[i][j] = allOrders.get(i).getProduct_id() + "  ";
                }
                if (j == 4) {
                    tableData[i][j] = allOrders.get(i).getProduct_name() + "  ";
                }
                if (j == 5) {
                    tableData[i][j] = allOrders.get(i).getProduct_quantity() + "  ";
                }
            }
        }

        TextTable table = new TextTable(tableHeaders, tableData);
        table.setAddRowNumbering(false);

        return table;
    }

    public void restockItems(String id) {
        List<String> pids = productService.getIdByWarehouseId(id);

        for (String p : pids) {
            productService.restockItems(p);
        }

    }

}
