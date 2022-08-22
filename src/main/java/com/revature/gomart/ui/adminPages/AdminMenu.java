package com.revature.gomart.ui.adminPages;

import com.revature.gomart.daos.*;
import com.revature.gomart.models.PageServices;
import com.revature.gomart.models.User;
import com.revature.gomart.services.*;
import com.revature.gomart.ui.LoginMenu;
import com.revature.gomart.ui.MenuIF;
import com.revature.gomart.ui.OrderPage;
import com.revature.gomart.ui.UserProfile;

import java.util.Scanner;

public class AdminMenu extends PageServices implements MenuIF {

    private final User user;

    public AdminMenu(User user, UserService userService, ProductService productService, OrderService orderService, OPService opService, AddressService addressService) {
        super(userService, productService, orderService, opService, addressService);
        this.user = user;
    }


    @Override
    public void start() {
        Scanner scan = new Scanner(System.in);

        exit:
        {
            while (true) {
                System.out.println("Welcome in, " + user.getFname());
                System.out.println("What would you like to do?: ");
                System.out.println(
                        "1. View warehouses        2. View customers " +
                        "\n3. View all orders      4. View profile " +
                        "\n5. Sign out             6. Exit the Pokemart");

                String productChoice = scan.nextLine();
                while (true) {
                    switch (productChoice) {
                        case "1":
                            new AdminWarehouseMenu(user, userService, productService, orderService, opService, addressService).start();
                            break exit;
                        case "2":
                            new AdminUserViewMenu(user, userService, productService, orderService, opService, addressService).start();
                            break exit;
                        case "3":
                            new OrderPage(user, userService, productService, orderService, opService, addressService).start();
                            break exit;
                        case "4":
                            new AdminProfile(user, userService, productService, orderService, opService, addressService).start();
                            break exit;
                        case "5":
                            System.out.println("\nThank you!");
                            new LoginMenu(new UserService(new UserDAO()), new ProductService(new ProductDAO()), new OrderService(new OrderDAO()), new OPService(new OpDAO()), new AddressService(new AddressDAO())).start();
                            break exit;
                        case "6":
                            System.out.println("\nThank you! \nWe hope to see you again!");
                            break exit;
                        default:
                            System.out.println("Input not recognized.");
                    }
                }
            }
        }
    }
}
