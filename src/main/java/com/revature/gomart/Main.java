package com.revature.gomart;

import com.revature.gomart.daos.*;
import com.revature.gomart.services.*;
import com.revature.gomart.ui.LoginMenu;
import com.revature.gomart.utils.database.ConnectionFactory;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        new LoginMenu(new UserService(new UserDAO()), new ProductService(new ProductDAO()), new OrderService(new OrderDAO()), new OPService(new OpDAO()), new AddressService(new AddressDAO())).start();

    }

}
