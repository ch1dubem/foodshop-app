package com.pluralsight.ui;

import com.pluralsight.signatures.SignatureRiceBowl;
import com.pluralsight.models.*;
import com.pluralsight.toppings.*;
import com.pluralsight.managers.ReceiptManager;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.stream.IntStream;

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

    public void printBanner() {
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

    public void homeScreen() {
        printBanner();
        boolean running = true;
        while (running) {
            try {
                System.out.println();
                System.out.println(GREEN + "┌──────────────────────────────────────────┐" + RESET);
                System.out.println(GREEN + "│" + RESET + WHITE + BOLD + "              HOME SCREEN                 " + RESET + GREEN + "│" + RESET);
                System.out.println(GREEN + "├──────────────────────────────────────────┤" + RESET);
                System.out.println(GREEN + "│" + RESET + YELLOW + "  1) " + WHITE + "New Order                          " + GREEN + "│" + RESET);
                System.out.println(GREEN + "│" + RESET + RED + "  0) " + WHITE + "Exit                               " + GREEN + "│" + RESET);
                System.out.println(GREEN + "└──────────────────────────────────────────┘" + RESET);
                System.out.print(CYAN + "  ➤ Your choice: " + RESET);

                String choice = scanner.nextLine().trim();

                switch (choice) {
                    case "1" -> {
                        currentOrder = new Order();
                        orderScreen();
                    }
                    case "0" -> {
                        System.out.println(GREEN + BOLD + "\n  🙏 Thank you for visiting Dubem's Naija Kitchen!" + RESET);
                        System.out.println(DIM + "     We hope to see you again soon!\n" + RESET);
                        running = false;
                    }
                    default -> System.out.println(RED + "  ✘ Invalid option." + RESET);
                }
            } catch (Exception e) {
                System.out.println(RED + "  ✘ Something went wrong." + RESET);
            }
        }
    }

    public void orderScreen() {
        boolean ordering = true;
        while (ordering) {
            try {
                System.out.println();
                System.out.println(GREEN + "┌──────────────────────────────────────────┐" + RESET);
                System.out.println(GREEN + "│" + RESET + WHITE + BOLD + "              ORDER MENU                  " + RESET + GREEN + "│" + RESET);
                System.out.println(GREEN + "├──────────────────────────────────────────┤" + RESET);
                System.out.println(GREEN + "│" + RESET + YELLOW + "  1) " + WHITE + "🍚 Add Rice Bowl                    " + GREEN + "│" + RESET);
                System.out.println(GREEN + "│" + RESET + YELLOW + "  2) " + WHITE + "🥤 Add Drink                        " + GREEN + "│" + RESET);
                System.out.println(GREEN + "│" + RESET + YELLOW + "  3) " + WHITE + "🥧 Add Pastry Side                  " + GREEN + "│" + RESET);
                System.out.println(GREEN + "│" + RESET + MAGENTA + "  4) " + WHITE + "⭐ Signature Bowls                  " + GREEN + "│" + RESET);
                System.out.println(GREEN + "├──────────────────────────────────────────┤" + RESET);
                System.out.println(GREEN + "│" + RESET + CYAN + "  5) " + WHITE + "💰 Checkout                         " + GREEN + "│" + RESET);
                System.out.println(GREEN + "│" + RESET + RED + "  0) " + WHITE + "✘  Cancel Order                     " + GREEN + "│" + RESET);
                System.out.println(GREEN + "└──────────────────────────────────────────┘" + RESET);
                System.out.print(CYAN + "  ➤ Your choice: " + RESET);

                String choice = scanner.nextLine().trim();

                switch (choice) {
                    case "1" -> addItemScreen();
                    case "2" -> addDrinkScreen();
                    case "3" -> addSideScreen();
                    case "4" -> signatureBowlScreen();
                    case "5" -> {
                        if (currentOrder.isEmpty()) {
                            System.out.println(RED + "  ✘ Your order is empty! Add at least a drink or pastry side." + RESET);
                        } else {
                            ordering = !checkoutScreen();
                        }
                    }
                    case "0" -> {
                        currentOrder = null;
                        System.out.println(RED + "  ✘ Order cancelled." + RESET);
                        ordering = false;
                    }
                    default -> System.out.println(RED + "  ✘ Invalid option." + RESET);
                }
            } catch (Exception e) {
                System.out.println(RED + "  ✘ Something went wrong." + RESET);
            }
        }
    }

    public boolean checkoutScreen() {
        try {
            System.out.println(currentOrder);

            while (true) {
                try {
                    System.out.println(GREEN + "  1) " + WHITE + "✔ Confirm Order" + RESET);
                    System.out.println(RED + "  0) " + WHITE + "✘ Cancel Order" + RESET);
                    System.out.print(CYAN + "  ➤ Choice: " + RESET);
                    String choice = scanner.nextLine().trim();

                    if (choice.equals("1")) {
                        receiptManager.saveReceipt(currentOrder);
                        System.out.println(GREEN + BOLD + "\n  ✔ Order confirmed!" + RESET);
                        System.out.println(GREEN + "  🙏 Thank you for eating at Dubem's Naija Kitchen!" + RESET);
                        currentOrder = null;
                        return true;
                    } else if (choice.equals("0")) {
                        currentOrder = null;
                        System.out.println(RED + "  ✘ Order cancelled." + RESET);
                        return true;
                    } else {
                        System.out.println(RED + "  ✘ Please enter 1 or 0." + RESET);
                    }
                } catch (Exception e) {
                    System.out.println(RED + "  ✘ Something went wrong." + RESET);
                }
            }
        } catch (Exception e) {
            System.out.println(RED + "  ✘ Error at checkout." + RESET);
            return false;
        }
    }

    public void addItemScreen() {
        try {
            System.out.println(GREEN + BOLD + "\n  ─── 🍚 Build Your Rice Bowl ───" + RESET);

            String riceType;
            while (true) {
                try {
                    System.out.println(YELLOW + "\n  Select rice type:" + RESET);
                    System.out.println(WHITE + "    1) Jollof");
                    System.out.println("    2) Fried");
                    System.out.println("    3) Coconut");
                    System.out.println("    4) White" + RESET);
                    System.out.print(CYAN + "  ➤ Choice: " + RESET);
                    String typeChoice = scanner.nextLine().trim();
                    switch (typeChoice) {
                        case "1" -> riceType = "Jollof";
                        case "2" -> riceType = "Fried";
                        case "3" -> riceType = "Coconut";
                        case "4" -> riceType = "White";
                        default -> {
                            System.out.println(RED + "  ✘ Please enter 1-4." + RESET);
                            continue;
                        }
                    }
                    break;
                } catch (Exception e) {
                    System.out.println(RED + "  ✘ Something went wrong." + RESET);
                }
            }

            String size;
            while (true) {
                try {
                    System.out.println(YELLOW + "\n  Select size:" + RESET);
                    System.out.printf(WHITE + "    1) Small   " + CYAN + "$7.99%n" + RESET);
                    System.out.printf(WHITE + "    2) Medium  " + CYAN + "$10.99%n" + RESET);
                    System.out.printf(WHITE + "    3) Large   " + CYAN + "$13.99%n" + RESET);
                    System.out.print(CYAN + "  ➤ Choice: " + RESET);
                    String sizeChoice = scanner.nextLine().trim();
                    switch (sizeChoice) {
                        case "1" -> size = "Small";
                        case "2" -> size = "Medium";
                        case "3" -> size = "Large";
                        default -> {
                            System.out.println(RED + "  ✘ Please enter 1-3." + RESET);
                            continue;
                        }
                    }
                    break;
                } catch (Exception e) {
                    System.out.println(RED + "  ✘ Something went wrong." + RESET);
                }
            }

            Rice rice = new Rice(size, riceType);

            addMeatToppings(rice);
            addFishToppings(rice);
            addRegularToppings(rice);
            addSauceToppings(rice);

            while (true) {
                try {
                    System.out.print(YELLOW + "\n  Make it spicy? 🌶️  (yes/no): " + RESET);
                    String spicy = scanner.nextLine().trim().toLowerCase();
                    if (spicy.equals("yes")) {
                        rice.setIsSpicy(true);
                        break;
                    } else if (spicy.equals("no")) {
                        rice.setIsSpicy(false);
                        break;
                    } else {
                        System.out.println(RED + "  ✘ Please enter yes or no." + RESET);
                    }
                } catch (Exception e) {
                    System.out.println(RED + "  ✘ Something went wrong." + RESET);
                }
            }

            currentOrder.addItem(rice);
            System.out.println(GREEN + BOLD + "\n  ✔ Rice bowl added to order!" + RESET);
            System.out.println(DIM + "  " + rice + RESET);
            System.out.printf(CYAN + "  Bowl Total: $%.2f%n" + RESET, rice.getPrice());

        } catch (Exception e) {
            System.out.println(RED + "  ✘ Error building rice bowl." + RESET);
        }
    }

    public void addMeatToppings(Rice rice) {
        String[] meats = {"Chicken", "Turkey", "Goat Meat", "Beef", "Lamb"};
        System.out.println(YELLOW + BOLD + "\n  ─── 🥩 Meat Toppings ───" + RESET);
        System.out.printf(DIM + "  Prices: Small $2.00 │ Medium $3.00 │ Large $4.00%n" + RESET);
        System.out.printf(DIM + "  Extra:  Small +$1.00 │ Medium +$1.50 │ Large +$2.00%n" + RESET);

        // Original loop:
        // for (int i = 0; i < meats.length; i++) {
        //     System.out.printf(WHITE + "    %d) %s%n" + RESET, i + 1, meats[i]);
        // }
        IntStream.range(0, meats.length)
                .forEach(i -> System.out.printf(WHITE + "    %d) %s%n" + RESET, i + 1, meats[i]));

        System.out.println(DIM + "    0) Done" + RESET);

        while (true) {
            try {
                System.out.print(CYAN + "  ➤ Add meat (0 to finish): " + RESET);
                int pick = Integer.parseInt(scanner.nextLine().trim());

                if (pick == 0) break;
                if (pick < 1 || pick > meats.length) {
                    System.out.println(RED + "  ✘ Invalid selection." + RESET);
                    continue;
                }

                MeatTopping meat = new MeatTopping(meats[pick - 1]);
                while (true) {
                    try {
                        System.out.print(YELLOW + "  Extra " + meats[pick - 1] + "? (yes/no): " + RESET);
                        String extraInput = scanner.nextLine().trim().toLowerCase();
                        if (extraInput.equals("yes")) {
                            meat.setExtra(true);
                            break;
                        } else if (extraInput.equals("no")) {
                            meat.setExtra(false);
                            break;
                        } else {
                            System.out.println(RED + "  ✘ Please enter yes or no." + RESET);
                        }
                    } catch (Exception e) {
                        System.out.println(RED + "  ✘ Something went wrong." + RESET);
                    }
                }

                rice.addTopping(meat);
                System.out.println(GREEN + "  ✔ Added: " + meat + " - " + CYAN + String.format("$%.2f", meat.getPrice(rice.getSize())) + RESET);

            } catch (NumberFormatException e) {
                System.out.println(RED + "  ✘ Please enter a valid number." + RESET);
            }
        }
    }

    public void addFishToppings(Rice rice) {
        String[] fish = {"Tilapia", "Catfish", "Croaker", "Red Snapper"};
        System.out.println(YELLOW + BOLD + "\n  ─── 🐟 Premium Fish Toppings ───" + RESET);
        System.out.printf(DIM + "  Prices: Small $2.50 │ Medium $3.50 │ Large $4.50%n" + RESET);
        System.out.printf(DIM + "  Extra:  Small +$1.25 │ Medium +$1.75 │ Large +$2.25%n" + RESET);

        // Original loop:
        // for (int i = 0; i < fish.length; i++) {
        //     System.out.printf(WHITE + "    %d) %s%n" + RESET, i + 1, fish[i]);
        // }
        IntStream.range(0, fish.length)
                .forEach(i -> System.out.printf(WHITE + "    %d) %s%n" + RESET, i + 1, fish[i]));

        System.out.println(DIM + "    0) Done" + RESET);

        while (true) {
            try {
                System.out.print(CYAN + "  ➤ Add fish (0 to finish): " + RESET);
                int pick = Integer.parseInt(scanner.nextLine().trim());

                if (pick == 0) break;
                if (pick < 1 || pick > fish.length) {
                    System.out.println(RED + "  ✘ Invalid selection." + RESET);
                    continue;
                }

                FishTopping fishTopping = new FishTopping(fish[pick - 1]);
                while (true) {
                    try {
                        System.out.print(YELLOW + "  Extra " + fish[pick - 1] + "? (yes/no): " + RESET);
                        String extraInput = scanner.nextLine().trim().toLowerCase();
                        if (extraInput.equals("yes")) {
                            fishTopping.setExtra(true);
                            break;
                        } else if (extraInput.equals("no")) {
                            fishTopping.setExtra(false);
                            break;
                        } else {
                            System.out.println(RED + "  ✘ Please enter yes or no." + RESET);
                        }
                    } catch (Exception e) {
                        System.out.println(RED + "  ✘ Something went wrong." + RESET);
                    }
                }

                rice.addTopping(fishTopping);
                System.out.println(GREEN + "  ✔ Added: " + fishTopping + " - " + CYAN + String.format("$%.2f", fishTopping.getPrice(rice.getSize())) + RESET);

            } catch (NumberFormatException e) {
                System.out.println(RED + "  ✘ Please enter a valid number." + RESET);
            }
        }
    }

    public void addRegularToppings(Rice rice) {
        String[] regulars = {"Plantain", "Beans", "Eggs", "Coleslaw", "Seaweed"};
        System.out.println(YELLOW + BOLD + "\n  ─── 🥗 Regular Toppings (Free) ───" + RESET);

        // Original loop:
        // for (int i = 0; i < regulars.length; i++) {
        //     System.out.printf(WHITE + "    %d) %s%n" + RESET, i + 1, regulars[i]);
        // }
        IntStream.range(0, regulars.length)
                .forEach(i -> System.out.printf(WHITE + "    %d) %s%n" + RESET, i + 1, regulars[i]));

        System.out.println(DIM + "    0) Done" + RESET);

        while (true) {
            try {
                System.out.print(CYAN + "  ➤ Add topping (0 to finish): " + RESET);
                int pick = Integer.parseInt(scanner.nextLine().trim());

                if (pick == 0) break;
                if (pick < 1 || pick > regulars.length) {
                    System.out.println(RED + "  ✘ Invalid selection." + RESET);
                    continue;
                }

                rice.addTopping(new RegularTopping(regulars[pick - 1]));
                System.out.println(GREEN + "  ✔ Added: " + regulars[pick - 1] + RESET);

            } catch (NumberFormatException e) {
                System.out.println(RED + "  ✘ Please enter a valid number." + RESET);
            }
        }
    }

    public void addSauceToppings(Rice rice) {
        String[] sauces = {"Ofada Stew", "Peppered Stew", "Ofe Akwu", "Efe Riro"};
        System.out.println(YELLOW + BOLD + "\n  ─── 🍲 Stew/Condiments (Free) ───" + RESET);

        // Original loop:
        // for (int i = 0; i < sauces.length; i++) {
        //     System.out.printf(WHITE + "    %d) %s%n" + RESET, i + 1, sauces[i]);
        // }
        IntStream.range(0, sauces.length)
                .forEach(i -> System.out.printf(WHITE + "    %d) %s%n" + RESET, i + 1, sauces[i]));

        System.out.println(DIM + "    0) Done" + RESET);

        while (true) {
            try {
                System.out.print(CYAN + "  ➤ Add stew (0 to finish): " + RESET);
                int pick = Integer.parseInt(scanner.nextLine().trim());

                if (pick == 0) break;
                if (pick < 1 || pick > sauces.length) {
                    System.out.println(RED + "  ✘ Invalid selection." + RESET);
                    continue;
                }

                rice.addTopping(new SauceTopping(sauces[pick - 1]));
                System.out.println(GREEN + "  ✔ Added: " + sauces[pick - 1] + RESET);

            } catch (NumberFormatException e) {
                System.out.println(RED + "  ✘ Please enter a valid number." + RESET);
            }
        }
    }

    public void addDrinkScreen() {
        try {
            System.out.println(GREEN + BOLD + "\n  ─── 🥤 Add a Drink ───" + RESET);

            String flavor;
            while (true) {
                try {
                    System.out.println(YELLOW + "  Select flavor:" + RESET);
                    System.out.println(WHITE + "    1) Zobo");
                    System.out.println("    2) Chapman" + RESET);
                    System.out.print(CYAN + "  ➤ Choice: " + RESET);
                    String flavorChoice = scanner.nextLine().trim();
                    switch (flavorChoice) {
                        case "1" -> flavor = "Zobo";
                        case "2" -> flavor = "Chapman";
                        default -> {
                            System.out.println(RED + "  ✘ Please enter 1 or 2." + RESET);
                            continue;
                        }
                    }
                    break;
                } catch (Exception e) {
                    System.out.println(RED + "  ✘ Something went wrong." + RESET);
                }
            }

            String size;
            while (true) {
                try {
                    System.out.println(YELLOW + "\n  Select size:" + RESET);
                    System.out.printf(WHITE + "    1) Small   " + CYAN + "$3.00%n" + RESET);
                    System.out.printf(WHITE + "    2) Medium  " + CYAN + "$4.00%n" + RESET);
                    System.out.printf(WHITE + "    3) Large   " + CYAN + "$5.00%n" + RESET);
                    System.out.print(CYAN + "  ➤ Choice: " + RESET);
                    String sizeChoice = scanner.nextLine().trim();
                    switch (sizeChoice) {
                        case "1" -> size = "Small";
                        case "2" -> size = "Medium";
                        case "3" -> size = "Large";
                        default -> {
                            System.out.println(RED + "  ✘ Please enter 1-3." + RESET);
                            continue;
                        }
                    }
                    break;
                } catch (Exception e) {
                    System.out.println(RED + "  ✘ Something went wrong." + RESET);
                }
            }

            Drink drink = new Drink(size, flavor);
            currentOrder.addItem(drink);
            System.out.printf(GREEN + "  ✔ Added: %s - " + CYAN + "$%.2f%n" + RESET, drink, drink.getPrice());

        } catch (Exception e) {
            System.out.println(RED + "  ✘ Error adding drink." + RESET);
        }
    }

    public void addSideScreen() {
        try {
            System.out.println(GREEN + BOLD + "\n  ─── 🥧 Add a Pastry Side ───" + RESET);
            System.out.println(DIM + "  All pastries: $4.99" + RESET);

            String type;
            while (true) {
                try {
                    System.out.println(WHITE + "    1) Puff Puff");
                    System.out.println("    2) Meat Pie");
                    System.out.println("    3) Egg Roll" + RESET);
                    System.out.print(CYAN + "  ➤ Choice: " + RESET);
                    String choice = scanner.nextLine().trim();
                    switch (choice) {
                        case "1" -> type = "Puff Puff";
                        case "2" -> type = "Meat Pie";
                        case "3" -> type = "Egg Roll";
                        default -> {
                            System.out.println(RED + "  ✘ Please enter 1-3." + RESET);
                            continue;
                        }
                    }
                    break;
                } catch (Exception e) {
                    System.out.println(RED + "  ✘ Something went wrong." + RESET);
                }
            }

            Pastries pastry = new Pastries(type);
            currentOrder.addItem(pastry);
            System.out.printf(GREEN + "  ✔ Added: %s - " + CYAN + "$%.2f%n" + RESET, pastry, pastry.getPrice());

        } catch (Exception e) {
            System.out.println(RED + "  ✘ Error adding pastry." + RESET);
        }
    }

    public void signatureBowlScreen() {
        try {
            ArrayList<Rice> bowls = SignatureRiceBowl.getSignatureBowls();
            String[] names = SignatureRiceBowl.getSignatureNames();

            System.out.println(MAGENTA + BOLD + "\n  ─── ⭐ Signature Rice Bowls ───" + RESET);

            // Original loop:
            // for (int i = 0; i < names.length; i++) {
            //     System.out.printf(YELLOW + "\n  %d) %s" + CYAN + " - $%.2f%n" + RESET, i + 1, names[i], bowls.get(i).getPrice());
            //     System.out.printf(DIM + "     %s %s Rice%n" + RESET, bowls.get(i).getSize(), bowls.get(i).getRiceType());
            //     for (Topping t : bowls.get(i).getToppings()) {
            //         System.out.println(DIM + "       + " + t.getName() + RESET);
            //     }
            // }
            IntStream.range(0, names.length)
                    .forEach(i -> {
                        System.out.printf(YELLOW + "\n  %d) %s" + CYAN + " - $%.2f%n" + RESET, i + 1, names[i], bowls.get(i).getPrice());
                        System.out.printf(DIM + "     %s %s Rice%n" + RESET, bowls.get(i).getSize(), bowls.get(i).getRiceType());
                        bowls.get(i).getToppings().forEach(t ->
                                System.out.println(DIM + "       + " + t.getName() + RESET));
                    });

            System.out.println(DIM + "\n  0) Back" + RESET);

            int pick;
            while (true) {
                try {
                    System.out.print(CYAN + "  ➤ Choice: " + RESET);
                    pick = Integer.parseInt(scanner.nextLine().trim());
                    if (pick == 0) return;
                    if (pick >= 1 && pick <= bowls.size()) break;
                    System.out.println(RED + "  ✘ Please enter 0-" + bowls.size() + "." + RESET);
                } catch (NumberFormatException e) {
                    System.out.println(RED + "  ✘ Please enter a valid number." + RESET);
                }
            }

            Rice selected = bowls.get(pick - 1);
            System.out.printf(GREEN + "\n  Selected: %s%n" + RESET, names[pick - 1]);

            while (true) {
                try {
                    System.out.print(YELLOW + "  Customize it? (yes/no): " + RESET);
                    String customize = scanner.nextLine().trim().toLowerCase();
                    if (customize.equals("yes")) {
                        System.out.println(WHITE + "\n  Current toppings:" + RESET);

                        // Original loop:
                        // for (int i = 0; i < selected.getToppings().size(); i++) {
                        //     System.out.printf(DIM + "    %d) %s%n" + RESET, i + 1, selected.getToppings().get(i).getName());
                        // }
                        IntStream.range(0, selected.getToppings().size())
                                .forEach(i -> System.out.printf(DIM + "    %d) %s%n" + RESET, i + 1, selected.getToppings().get(i).getName()));

                        System.out.print(YELLOW + "  Remove a topping? Enter name (or 'done'): " + RESET);
                        while (true) {
                            String input = scanner.nextLine().trim();
                            if (input.equalsIgnoreCase("done")) break;
                            selected.removeTopping(input);
                            System.out.println(RED + "  ✘ Removed: " + input + RESET);
                            System.out.print(YELLOW + "  Remove another? (name or 'done'): " + RESET);
                        }

                        while (true) {
                            try {
                                System.out.print(YELLOW + "  Add more toppings? (yes/no): " + RESET);
                                String addMore = scanner.nextLine().trim().toLowerCase();
                                if (addMore.equals("yes")) {
                                    addMeatToppings(selected);
                                    addFishToppings(selected);
                                    addRegularToppings(selected);
                                    addSauceToppings(selected);
                                    break;
                                } else if (addMore.equals("no")) {
                                    break;
                                } else {
                                    System.out.println(RED + "  ✘ Please enter yes or no." + RESET);
                                }
                            } catch (Exception e) {
                                System.out.println(RED + "  ✘ Something went wrong." + RESET);
                            }
                        }
                        break;
                    } else if (customize.equals("no")) {
                        break;
                    } else {
                        System.out.println(RED + "  ✘ Please enter yes or no." + RESET);
                    }
                } catch (Exception e) {
                    System.out.println(RED + "  ✘ Something went wrong." + RESET);
                }
            }

            while (true) {
                try {
                    System.out.print(YELLOW + "\n  Make it spicy? 🌶️  (yes/no): " + RESET);
                    String spicy = scanner.nextLine().trim().toLowerCase();
                    if (spicy.equals("yes")) {
                        selected.setIsSpicy(true);
                        break;
                    } else if (spicy.equals("no")) {
                        selected.setIsSpicy(false);
                        break;
                    } else {
                        System.out.println(RED + "  ✘ Please enter yes or no." + RESET);
                    }
                } catch (Exception e) {
                    System.out.println(RED + "  ✘ Something went wrong." + RESET);
                }
            }

            currentOrder.addItem(selected);
            System.out.println(GREEN + BOLD + "\n  ✔ Signature bowl added!" + RESET);
            System.out.println(DIM + "  " + selected + RESET);
            System.out.printf(CYAN + "  Bowl Total: $%.2f%n" + RESET, selected.getPrice());

        } catch (Exception e) {
            System.out.println(RED + "  ✘ Error." + RESET);
        }
    }
}