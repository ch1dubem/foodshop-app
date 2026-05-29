package com.pluralsight.ui;
import com.pluralsight.signatures.SignatureRiceBowl;
import java.util.ArrayList;

import com.pluralsight.models.*;
import com.pluralsight.toppings.*;
import com.pluralsight.managers.ReceiptManager;
import java.util.Scanner;

public class UserInterface {

    private static final String RESET = "\033[0m";
    private static final String BOLD = "\033[1m";
    private static final String GREEN = "\033[32m";
    private static final String YELLOW = "\033[33m";
    private static final String CYAN = "\033[36m";
    private static final String RED = "\033[31m";
    private static final String WHITE = "\033[37m";
    private static final String MAGENTA = "\033[35m";
    private static final String BG_GREEN = "\033[42m";
    private static final String BLACK = "\033[30m";
    private static final String DIM = "\033[2m";

    Scanner scanner = new Scanner(System.in);
    Order currentOrder;
    ReceiptManager receiptManager = new ReceiptManager();

    public void display() {
        homeScreen();
    }


    public void homeScreen() {
        printBanner();
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
        try {
            ArrayList<Rice> bowls = SignatureRiceBowl.getSignatureBowls();
            String[] names = SignatureRiceBowl.getSignatureNames();

            System.out.println("\n--- Signature Rice Bowls ---");
            for (int i = 0; i < names.length; i++) {
                System.out.printf("\n  %d) %s - $%.2f%n", i + 1, names[i], bowls.get(i).getPrice());
                System.out.printf("     %s %s Rice%n", bowls.get(i).getSize(), bowls.get(i).getRiceType());
                for (Topping t : bowls.get(i).getToppings()) {
                    System.out.println("       + " + t.getName());
                }
            }
            System.out.println("\n  0) Back");
            System.out.print("Choice: ");
            String choice = scanner.nextLine().trim();

            int pick = Integer.parseInt(choice);

            if (pick == 0) return;
            if (pick < 1 || pick > bowls.size()) {
                System.out.println("Invalid selection.");
                return;
            }

            Rice selected = bowls.get(pick - 1);
            System.out.printf("\nSelected: %s%n", names[pick - 1]);

            System.out.print("Customize it? (yes/no): ");
            String customize = scanner.nextLine().trim();

            if (customize.equalsIgnoreCase("yes")) {
                System.out.println("\nCurrent toppings:");
                for (int i = 0; i < selected.getToppings().size(); i++) {
                    System.out.printf("  %d) %s%n", i + 1, selected.getToppings().get(i).getName());
                }

                System.out.print("Remove a topping? Enter name (or 'done'): ");
                while (true) {
                    String input = scanner.nextLine().trim();
                    if (input.equalsIgnoreCase("done")) break;
                    selected.removeTopping(input);
                    System.out.println("Removed: " + input);
                    System.out.print("Remove another? (name or 'done'): ");
                }

                System.out.print("Add more toppings? (yes/no): ");
                if (scanner.nextLine().trim().equalsIgnoreCase("yes")) {
                    addMeatToppings(selected);
                    addFishToppings(selected);
                    addRegularToppings(selected);
                    addSauceToppings(selected);
                }
            }

            currentOrder.addItem(selected);
            System.out.println("\nSignature bowl added!");
            System.out.println(selected);
            System.out.printf("Bowl Total: $%.2f%n", selected.getPrice());

        } catch (NumberFormatException e) {
            System.out.println("Please enter a valid number.");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
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
        try {
            System.out.println("\n--- Build Your Rice Bowl ---");

            System.out.println("Select rice type:");
            System.out.println("  1) Jollof");
            System.out.println("  2) Fried");
            System.out.println("  3) Coconut");
            System.out.println("  4) White");
            System.out.print("Choice: ");
            String typeChoice = scanner.nextLine().trim();

            String riceType = switch (typeChoice) {
                case "2" -> "Fried";
                case "3" -> "Coconut";
                case "4" -> "White";
                default -> "Jollof";
            };

            System.out.println("\nSelect size:");
            System.out.println("  1) Small  - $7.99");
            System.out.println("  2) Medium - $10.99");
            System.out.println("  3) Large  - $13.99");
            System.out.print("Choice: ");
            String sizeChoice = scanner.nextLine().trim();

            String size = switch (sizeChoice) {
                case "2" -> "Medium";
                case "3" -> "Large";
                default -> "Small";
            };

            Rice rice = new Rice(size, riceType);

            addMeatToppings(rice);
            addFishToppings(rice);
            addRegularToppings(rice);
            addSauceToppings(rice);

            System.out.print("\nMake it spicy? (yes/no): ");
            String spicy = scanner.nextLine().trim();
            rice.setIsSpicy(spicy.equalsIgnoreCase("yes"));

            currentOrder.addItem(rice);
            System.out.println("\nRice bowl added to order!");
            System.out.println(rice);
            System.out.printf("Bowl Total: $%.2f%n", rice.getPrice());

        } catch (Exception e) {
            System.out.println("Error building rice bowl: ");
        }
    }

    public void addMeatToppings(Rice rice) {
        String[] meats = {"Chicken", "Turkey", "Goat Meat", "Beef", "Lamb"};
        System.out.println("\n--- Meat Toppings ---");
        System.out.println("Prices: Small $2.00 | Medium $3.00 | Large $4.00");
        System.out.println("Extra:  Small +$1.00 | Medium +$1.50 | Large +$2.00");
        for (int i = 0; i < meats.length; i++) {
            System.out.printf("  %d) %s%n", i + 1, meats[i]);
        }
        System.out.println("  0) Done");

        while (true) {
            try {
                System.out.print("Add meat (0 to finish): ");
                int pick = Integer.parseInt(scanner.nextLine().trim());

                if (pick == 0) break;
                if (pick < 1 || pick > meats.length) {
                    System.out.println("Invalid selection.");
                    continue;
                }

                MeatTopping meat = new MeatTopping(meats[pick - 1]);
                System.out.print("Extra " + meats[pick - 1] + "? (yes/no): ");
                meat.setExtra(scanner.nextLine().trim().equalsIgnoreCase("yes"));

                rice.addTopping(meat);
                System.out.println("Added: " + meat);

            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            }
        }
    }

    public void addFishToppings(Rice rice) {
        String[] fish = {"Tilapia", "Catfish", "Croaker", "Red Snapper"};
        System.out.println("\n--- Premium Fish Toppings ---");
        System.out.println("Prices: Small $2.50 | Medium $3.50 | Large $4.50");
        System.out.println("Extra:  Small +$1.25 | Medium +$1.75 | Large +$2.25");
        for (int i = 0; i < fish.length; i++) {
            System.out.printf("  %d) %s%n", i + 1, fish[i]);
        }
        System.out.println("  0) Done");

        while (true) {
            try {
                System.out.print("Add fish (0 to finish): ");
                int pick = Integer.parseInt(scanner.nextLine().trim());

                if (pick == 0) break;
                if (pick < 1 || pick > fish.length) {
                    System.out.println("Invalid selection.");
                    continue;
                }

                FishTopping fishTopping = new FishTopping(fish[pick - 1]);
                System.out.print("Extra " + fish[pick - 1] + "? (yes/no): ");
                fishTopping.setExtra(scanner.nextLine().trim().equalsIgnoreCase("yes"));

                rice.addTopping(fishTopping);
                System.out.println("Added: " + fishTopping);

            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            }
        }
    }

    public void addRegularToppings(Rice rice) {
        String[] regulars = {"Plantain", "Beans", "Eggs", "Coleslaw", "Seaweed"};
        System.out.println("\n--- Regular Toppings (Free) ---");
        for (int i = 0; i < regulars.length; i++) {
            System.out.printf("  %d) %s%n", i + 1, regulars[i]);
        }
        System.out.println("  0) Done");

        while (true) {
            try {
                System.out.print("Add topping (0 to finish): ");
                int pick = Integer.parseInt(scanner.nextLine().trim());

                if (pick == 0) break;
                if (pick < 1 || pick > regulars.length) {
                    System.out.println("Invalid selection.");
                    continue;
                }

                rice.addTopping(new RegularTopping(regulars[pick - 1]));
                System.out.println("Added: " + regulars[pick - 1]);

            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            }
        }
    }

    public void addSauceToppings(Rice rice) {
        String[] sauces = {"Ofada Stew", "Peppered Stew", "Ofe Akwu", "Efe Riro"};
        System.out.println("\n--- Stew/Condiments (Free) ---");
        for (int i = 0; i < sauces.length; i++) {
            System.out.printf("  %d) %s%n", i + 1, sauces[i]);
        }
        System.out.println("  0) Done");

        while (true) {
            try {
                System.out.print("Add stew (0 to finish): ");
                int pick = Integer.parseInt(scanner.nextLine().trim());

                if (pick == 0) break;
                if (pick < 1 || pick > sauces.length) {
                    System.out.println("Invalid selection.");
                    continue;
                }

                rice.addTopping(new SauceTopping(sauces[pick - 1]));
                System.out.println("Added: " + sauces[pick - 1]);

            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            }
        }
    }


    private void printBanner() {
        System.out.println(GREEN + "  ═══════════════════════════════════════════════════════════════" + RESET);
        System.out.println(GREEN + BOLD +
                "     ____        __                  __    \n" +
                "    / __ \\__  __/ /_  ___  ____ ___ ( _)___ \n" +
                "   / / / / / / / __ \\/ _ \\/ __ `__ \\/ / ___/ \n" +
                "  / /_/ / /_/ / /_/ /  __/ / / / / / (__  )  \n" +
                " /_____/\\__,_/_.___/\\___/_/ /_/ /_/_/____/   " + RESET);
        System.out.println(WHITE + BOLD +
                "     _   __      _  _          \n" +
                "    / | / /___ _(_)(_)___ _    \n" +
                "   /  |/ / __ `/ / / __ `/    \n" +
                "  / /|  / /_/ / / / /_/ /     \n" +
                " /_/ |_/\\__,_/_/ /\\__,_/      \n" +
                "             /___/             " + RESET);
        System.out.println(GREEN + BOLD +
                "     __ __ _ __       __                 \n" +
                "    / //_/(_) /______/ /_  ___  ____     \n" +
                "   / ,<  / / __/ ___/ __ \\/ _ \\/ __ \\    \n" +
                "  / /| |/ / /_/ /__/ / / /  __/ / / /    \n" +
                " /_/ |_/_/\\__/\\___/_/ /_/\\___/_/ /_/     " + RESET);
        System.out.println(GREEN + "  ═══════════════════════════════════════════════════════════════" + RESET);
        System.out.println(YELLOW + "          Welcome to Dubem's Naija Kitchen!" + RESET);
        System.out.println(DIM + "         \"Taste of Home, One Bowl at a Time\"" + RESET);
        System.out.println(GREEN + "  ═══════════════════════════════════════════════════════════════" + RESET);
    }






}
