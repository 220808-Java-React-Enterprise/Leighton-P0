package com.revature.gomart.ui;

import com.revature.gomart.models.Manager;

import java.util.Scanner;

public class AdminLogin implements MenuIF{

    public AdminLogin() {};
    @Override
    public void start() {
        String userInput = "";
        Scanner scan = new Scanner(System.in);

        exit:
        {
            while (true) {
                System.out.println("\nManager menu");
                System.out.println("Press one of the following keys to continue:");
                System.out.println("[1] Login to an existing manager account");
                System.out.println("[2] Register as a new manager");
                System.out.println("[3] Exit the Pokemart");

                System.out.println("\nEnter:");
                userInput = scan.nextLine();

                switch (userInput) {
                    case "1":
                        login();
                        break;
                    case "2":
                        Manager manager = signup();
                        new LandingPage(manager).start();
                        break;
                    case "3":
                        System.out.println("We hope to see you again!");
                        break exit;

                }
            }
        }
    }

}
