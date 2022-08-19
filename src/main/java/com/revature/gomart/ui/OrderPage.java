package com.revature.gomart.ui;
import com.revature.gomart.daos.*;
import com.revature.gomart.models.*;
import com.revature.gomart.services.*;
import com.revature.gomart.utils.custom_exceptions.*;

public class OrderPage implements MenuIF{

        private final User user;

        private final UserService userService;

        private final ProductService productService;

        public OrderPage(User user, UserService userService, ProductService productService) {
            this.user = user;
            this.userService = userService;
            this.productService = productService;
        }
    @Override
    public void start() {
        System.out.println("Cart is under construction; thank you for your patience.");

    }
}
