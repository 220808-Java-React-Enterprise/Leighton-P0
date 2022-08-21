package com.revature.gomart.ui;

import com.revature.gomart.models.*;
import com.revature.gomart.services.*;
import dnl.utils.text.table.TextTable;

import java.util.List;
import java.util.Scanner;
import java.time.LocalDate;

public class CheckoutPage extends PageServices implements MenuIF {

    private final User user;

    public CheckoutPage(User user, UserService userService, ProductService productService, OrderService orderService, OPService opService, AddressService addressService) {
        super(userService, productService, orderService, opService, addressService);
        this.user = user;
    }

    @Override
    public void start() {
//        System.out.println("We're working on it!");
//        new OrderPage(user, userService, productService, orderService, opService, addressService).start();
        Scanner scan = new Scanner(System.in);
        Order currentOrder = orderService.retrieve(false, user.getId());
        List<OrderProduct> products = opService.getOrderProducts(currentOrder.getId());


        exit:
        {
            while (true) {
                if (addressService.retrieve(user.getId()) == null) {
                    new AddressPage(user, userService, productService, orderService, opService, addressService).start();
                } else {
                System.out.println("Checkout: \n");
                    System.out.println("Is this the address you wish to ship to?");
                    Address userAddress = addressService.retrieve(user.getId());
                }

            }
        }

    }
}
