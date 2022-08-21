package com.revature.gomart.ui;

import com.revature.gomart.models.User;
import com.revature.gomart.services.*;

import java.util.Scanner;

public class AddressPage implements MenuIF {

    private final User user;
    private final UserService userService;
//    private final ProductService productService;
//    private final OrderService orderService;
//    private final OPService opService;
    private final AddressService addressService;

    public AddressPage(User user, UserService userService, AddressService addressService) {
        this.user = user;
        this.userService = userService;
        this.addressService = addressService;
    }

    @Override
    public void start() {
        Scanner scan = new Scanner(System.in);
    }
}
