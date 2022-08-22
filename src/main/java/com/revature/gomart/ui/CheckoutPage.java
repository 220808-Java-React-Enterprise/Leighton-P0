package com.revature.gomart.ui;

import com.revature.gomart.models.*;
import com.revature.gomart.services.*;
import dnl.utils.text.table.TextTable;

import java.util.List;
import java.util.Scanner;
import java.time.LocalDate;
import java.util.UUID;

public class CheckoutPage extends PageServices implements MenuIF {

    private final User user;

    public CheckoutPage(User user, UserService userService, ProductService productService, OrderService orderService, OPService opService, AddressService addressService) {
        super(userService, productService, orderService, opService, addressService);
        this.user = user;
    }

    @Override
    public void start() {
        Scanner scan = new Scanner(System.in);
        Order currentOrder = orderService.retrieve(false, user.getId());
        List<OrderProduct> products = opService.getOrderProducts(currentOrder.getId());

        LocalDate today = LocalDate.now();

        exit:
        {
            while (true) {
                if (addressService.retrieve(user.getId()) == null) {
                    new AddressPage(user, userService, productService, orderService, opService, addressService).createNewAddress();
                } else {
                    choiceExit:
                    {
                        while (true) {
                            System.out.println("Checkout: \n");
                            System.out.println("Is this the address you wish to ship to?");
                            Address userAddress = addressService.retrieve(user.getId());
                            System.out.println(
                                "Name: " + userAddress.getFullName() + " \n" +
                                "Street: " + userAddress.getStreet() + " \n" +
                                "City: " + userAddress.getCity() + " \n" +
                                "Region: " + userAddress.getRegion() + " \n"
                            );
                            System.out.println("(y/n): \n");
                            switch (scan.nextLine()) {
                                case "n":
                                    userAddress = new AddressPage(user, userService, productService, orderService, opService, addressService).editAddress(userAddress);
                                    addressService.updateAddress(userAddress);

                                case "y":
                                    currentOrder.setOrderDate(LocalDate.now());
                                    System.out.println("Please select your shipping option \n");
                                    System.out.println("1. Standard one-day shipping \n2. Expedited same-day shipping \n3. Pick-up at local branch \n");
                                    switch (scan.nextLine()) {
                                        case "1":
                                            currentOrder.setDeliveryDate(today.plusDays(1));
                                            currentOrder.setDeliveryType("Standard");
                                            break choiceExit;
                                        case "2":
                                            currentOrder.setDeliveryDate(today);
                                            currentOrder.setDeliveryType("Expedited");
                                            break choiceExit;
                                        case "3":
                                            currentOrder.setDeliveryType("Pick up in store");
                                            break choiceExit;
                                    }
                            }
                        }
                    }
                    System.out.println("\nConfirm order \nOrder details:");
                    TextTable table = new OrderPage(user, userService, productService, orderService, opService, addressService).generateOrder(products,currentOrder);
                    table.printTable();
                    System.out.println("\nOrder total: " + currentOrder.getTot_price());
                    if (currentOrder.getDeliveryType() != "Pick up in store") {
                        System.out.println("Delivery date: " + currentOrder.getDeliveryDate());
                    } else {
                        System.out.println("You will receive an email at " + user.getEmail() + " when your order is ready for pickup");
                    }

                    System.out.println("\n1. Place order \n2. Back to order page");
                    switch(scan.nextLine()) {
                        case "1":
                            currentOrder.setOrderComplete(true);
                            orderService.updateOrderStatus(currentOrder);
                            System.out.println("\nThank you for your purchase! \nTo see your order details, check your order history in your profile.");
                            orderService.createNew(new Order(
                                    UUID.randomUUID().toString(),
                                    false,
                                    user.getId()
                            ));
                            new LandingPage(user, userService, productService, orderService, opService, addressService).start();
                            break exit;
                        case "2":
                            orderService.updateOrderStatus(currentOrder);
                            new OrderPage(user, userService, productService, orderService, opService, addressService).start();
                            break exit;
                    }
                }
            }
        }
    }
}
