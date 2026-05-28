package com.pluralsight.ui;

import com.pluralsight.models.*;
import com.pluralsight.toppings.*;
import com.pluralsight.managers.ReceiptManager;
import java.util.Scanner;

public class UserInterface {
    Scanner scanner = new Scanner(System.in);
    Order currentOrder;
    ReceiptManager receiptManager = new ReceiptManager();

    public void display() {
        homeScreen();
    }


    public void homeScreen() {
        boolean running = true;
        while (running) {
            System.out.println("\n==========================================");
            System.out.println("        DUBEM'S NAIJA KITCHEN");
            System.out.println("   \"Taste of Home, One Bowl at a Time\"");
            System.out.println("==========================================");
            System.out.println("  HOME SCREEN");
            System.out.println("  1) New Order");
            System.out.println("  0) Exit");
            System.out.println("------------------------------------------");
            System.out.print("Enter your choice: ");

            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1" -> {
                    currentOrder = new Order();
                    orderScreen();
                }
                case "0" -> {
                    System.out.println("Thank you for visiting Dubem's Naija Kitchen!");
                    running = false;
                }
                default -> System.out.println("Invalid option.");
            }
        }
    }

    public void orderScreen() {}



}
