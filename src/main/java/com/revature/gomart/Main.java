package com.revature.gomart;

import com.revature.gomart.daos.UserDAO;
import com.revature.gomart.services.UserService;
import com.revature.gomart.ui.LoginMenu;
import com.revature.gomart.utils.database.ConnectionFactory;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        /* dependency injection */

        new
                LoginMenu(new UserService(new UserDAO())).start();

    }
}
