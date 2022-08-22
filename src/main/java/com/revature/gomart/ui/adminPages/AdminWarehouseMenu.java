package com.revature.gomart.ui.adminPages;

import com.revature.gomart.daos.*;
import com.revature.gomart.models.*;
import com.revature.gomart.services.*;
import com.revature.gomart.ui.MenuIF;

public class AdminWarehouseMenu extends PageServices implements MenuIF {

    private final User user;

    public AdminWarehouseMenu(User user, UserService userService, ProductService productService, OrderService orderService, OPService opService, AddressService addressService) {
        super(userService, productService, orderService, opService, addressService);
        this.user = user;
    }

    @Override
    public void start() {

    }
}
