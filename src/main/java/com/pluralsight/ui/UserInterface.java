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

    public void orderScreen() {
        boolean ordering = true;
        while (ordering) {
            try {
                System.out.println("\n  ORDER SCREEN");
                System.out.println("  1) Add Rice Bowl");
                System.out.println("  2) Add Drink");
                System.out.println("  3) Add Pastry Side");
                System.out.println("  4) Signature Bowls");
                System.out.println("  5) Checkout");
                System.out.println("  0) Cancel Order");
                System.out.println("------------------------------------------");
                System.out.print("Enter your choice: ");

                String choice = scanner.nextLine().trim();

                switch (choice) {
                    case "1" -> addItemScreen();
                    case "2" -> addDrinkScreen();
                    case "3" -> addSideScreen();
                    case "4" -> signatureBowlScreen();
                    case "5" -> {
                        if (currentOrder.isEmpty()) {
                            System.out.println("Your order is empty! Add at least a drink or pastry side.");
                        } else {
                            ordering = !checkoutScreen();
                        }
                    }
                    case "0" -> {
                        currentOrder = null;
                        System.out.println("Order cancelled.");
                        ordering = false;
                    }
                    default -> System.out.println("Invalid option.");
                }
            } catch (Exception e) {
                System.out.println("Something went wrong: ");
            }

        }
    }


    public boolean checkoutScreen() {
        try {
            System.out.println("\n" + currentOrder);
            System.out.println("  1) Confirm Order");
            System.out.println("  0) Cancel Order");
            System.out.print("Your choice: ");
            String choice = scanner.nextLine().trim();

            if (choice.equals("1")) {
                receiptManager.saveReceipt(currentOrder);
                System.out.println("Order confirmed! Thank you for eating at Dubem's Naija Kitchen!");
                currentOrder = null;
                return true;
            } else {
                currentOrder = null;
                System.out.println("Order cancelled.");
                return true;
            }
        } catch (Exception e) {
            System.out.println("Error at checkout: " + e.getMessage());
            return false;
        }
    }


    public void signatureBowlScreen() {
    }

    public void addSideScreen() {
    }

    public void addDrinkScreen() {
    }

    public void addItemScreen() {
    }


}
