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
            System.out.println("Error at checkout: ");
            return false;
        }
    }


    public void signatureBowlScreen() {
    }

    public void addSideScreen() {try {
        System.out.println("\n--- Add a Pastry Side ($4.99) ---");
        System.out.println("  1) Puff Puff");
        System.out.println("  2) Meat Pie");
        System.out.println("  3) Egg Roll");
        System.out.print("Choice: ");
        String choice = scanner.nextLine().trim();

        String type = switch (choice) {
            case "2" -> "Meat Pie";
            case "3" -> "Egg Roll";
            default -> "Puff Puff";
        };

        Pastries pastry = new Pastries(type);
        currentOrder.addItem(pastry);
        System.out.printf("Added: %s - $%.2f%n", pastry, pastry.getPrice());

    } catch (Exception e) {
        System.out.println("Error adding pastry: " );
    }
    }

    public void addDrinkScreen() {
        try {
            System.out.println("\n--- Add a Drink ---");
            System.out.println("Select flavor:");
            System.out.println("  1) Zobo");
            System.out.println("  2) Chapman");
            System.out.print("Choice: ");
            String flavorChoice = scanner.nextLine().trim();

            String flavor = switch (flavorChoice) {
                case "1" -> "Zobo";
                case "2" -> "Chapman";
                default -> "Zobo";
            };

            System.out.println("\nSelect size:");
            System.out.println("  1) Small  - $3.00");
            System.out.println("  2) Medium - $4.00");
            System.out.println("  3) Large  - $5.00");
            System.out.print("Choice: ");
            String sizeChoice = scanner.nextLine().trim();

            String size = switch (sizeChoice) {
                case "2" -> "Medium";
                case "3" -> "Large";
                default -> "Small";
            };

            Drink drink = new Drink(size, flavor);
            currentOrder.addItem(drink);
            System.out.printf("Added: %s - $%.2f%n", drink, drink.getPrice());

        } catch (Exception e) {
            System.out.println("Error adding drink: ");
        }

    }

    public void addItemScreen() {
    }


}
