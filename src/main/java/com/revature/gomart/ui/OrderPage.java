package com.revature.gomart.ui;
import com.revature.gomart.daos.*;
import com.revature.gomart.models.*;
import com.revature.gomart.services.*;
import com.revature.gomart.utils.custom_exceptions.*;
import dnl.utils.text.table.TextTable;

import java.sql.SQLOutput;
import java.util.List;

public class OrderPage extends PageServices implements MenuIF{

        private final User user;
        public OrderPage(User user, UserService userService, ProductService productService, OrderService orderService, OPService opService, AddressService addressService) {
            super(userService, productService, orderService, opService, addressService);
            this.user = user;
        }

    @Override
    public void start() {
//        System.out.println("Cart is under construction; thank you for your patience.");
        Order currentOrder = orderService.retrieve(false, user.getId());
        List<OrderProduct> products = opService.getOrderProducts((currentOrder.getId()));

        System.out.println("Your cart: \n");

        String[] tableHeaders = {"Item", "No.  ","Price "};
        Object[][] tableData = new Object[products.size()][3];
        int totPrice = 0;

        for (int i = 0; i < products.size(); i++) {
            String productName = products.get(i).getProduct_name();
            int productQuantity = products.get(i).getProduct_quantity();
            int productPrice = products.get(i).getProduct_price();
            totPrice += (productQuantity * productPrice);

            for (int j = 0; j < 3; j++) {
                if (j == 0) {
                    tableData[i][j] = productName + "  ";
                } if (j == 1) {
                    tableData[i][j] = productQuantity + "  ";
                } if (j == 2) {
                    tableData[i][j] = (productQuantity * productPrice) + "  ";
                }
            }
        }

        currentOrder.setTot_price(totPrice);
        orderService.updateCost(currentOrder, totPrice);
        TextTable table = new TextTable(tableHeaders, tableData);
        table.setAddRowNumbering(false);

        table.printTable();
        System.out.println("\nTotal cost: " + orderService.getCost(currentOrder));

    }

    public TextTable generateOrder(List<OrderProduct> products, Order order) {
        String[] tableHeaders = {"Item", "No.  ","Price "};
        Object[][] tableData = new Object[products.size()][3];


        for (int i = 0; i < products.size(); i++) {
            String productName = products.get(i).getProduct_name();
            int productQuantity = products.get(i).getProduct_quantity();
            int productPrice = products.get(i).getProduct_price();

            for (int j = 0; j < 3; j++) {
                if (j == 0) {
                    tableData[i][j] = productName + "  ";
                } if (j == 1) {
                    tableData[i][j] = productQuantity + "  ";
                } if (j == 2) {
                    tableData[i][j] = (productQuantity * productPrice) + "  ";
                }
            }
            orderService.updateCost(order, (productQuantity * productPrice));
        }

        TextTable table = new TextTable(tableHeaders, tableData);
        table.setAddRowNumbering(false);

        return table;
    }
}
