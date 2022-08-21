package com.revature.gomart.ui;

import com.revature.gomart.models.*;
import com.revature.gomart.services.*;

public class CheckoutPage extends PageServices implements MenuIF {

    private final User user;

    public CheckoutPage(User user, UserService userService, ProductService productService, OrderService orderService, OPService opService, AddressService addressService) {
        super(userService, productService, orderService, opService, addressService);
        this.user = user;
    }

    @Override
    public void start() {
        System.out.println("We're working on it!");
        new OrderPage(user, userService, productService, orderService, opService, addressService).start();
    }
}
