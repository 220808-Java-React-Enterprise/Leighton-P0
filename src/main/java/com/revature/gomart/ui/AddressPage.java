package com.revature.gomart.ui;

import com.revature.gomart.models.*;
import com.revature.gomart.services.*;

import java.util.Scanner;

public class AddressPage extends PageServices implements MenuIF {

    private final User user;

    public AddressPage(User user, UserService userService, ProductService productService, OrderService orderService, OPService opService, AddressService addressService) {
        super(userService, productService, orderService, opService, addressService);
        this.user = user;
    }


    @Override
    public void start() {
        Scanner scan = new Scanner(System.in);
        System.out.println("We're working on it!");
        new UserProfile(user, userService, productService, orderService, opService, addressService).start();
    }
}
