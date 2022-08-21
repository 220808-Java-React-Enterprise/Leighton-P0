package com.revature.gomart.models;

import com.revature.gomart.services.*;

public abstract class PageServices {

    protected final UserService userService;
    protected final ProductService productService;
    protected final OrderService orderService;
    protected final OPService opService;
    protected final AddressService addressService;

    public PageServices(UserService userService, ProductService productService, OrderService orderService, OPService opService, AddressService addressService) {
        this.userService = userService;
        this.productService = productService;
        this.orderService = orderService;
        this.opService = opService;
        this.addressService = addressService;
    }
}
