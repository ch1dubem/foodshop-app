package com.pluralsight.models;

import com.pluralsight.models.interfaces.Priceable;

import java.util.ArrayList;
import java.util.List;

// The shopping cart — holds all items the customer has added
// Uses a single List<Priceable> for Rice, Drink, and Pastries
// Polymorphism lets getPrice() work correctly on each item type


public class Order {
    // One list holds everything — Rice, Drink, and Pastries all implement Price
    private List<Priceable> items;

    // ANSI color constants for styled receipt output in terminal
    private static final String RESET = "\033[0m";
    private static final String BOLD = "\033[1m";
    private static final String GREEN = "\033[32m";
    private static final String YELLOW = "\033[33m";
    private static final String CYAN = "\033[36m";
    private static final String WHITE = "\033[37m";
    private static final String DIM = "\033[2m";
    private static final String BG_GREEN = "\033[42m";
    private static final String BLACK = "\033[30m";

    // Constructor — initializes empty list
    public Order() {
        this.items = new ArrayList<>();
    }

    // Adds any item that implements Price to the order
    public void addItem(Priceable item) {
        items.add(item);
    }

    // Removes item by index with bounds checking to prevent crash
    public void removeItem(int index) {
        if (index >= 0 && index < items.size()) {
            items.remove(index);
        }
    }

    // Returns the list so other classes can loop through it
    public List<Priceable> getItems() {
        return items;
    }

    // Calculates the total by summing every item's getPrice()
    // Each item type knows how to calculate its own price — polymorphism
    public double getTotal() {

        // Original loop:
        // double total = 0;
        // for (Price item : items) {
        //     total += item.getPrice();
        // }
        // return total;

        return items.stream()
                .mapToDouble(Priceable::getPrice)
                .sum();
    }

    // Returns true if the order has no items at all
    public boolean isEmpty() {
        return items.isEmpty();
    }

    // Checks if there's at least one Drink or Pastries in the order
    // if 0 rice bowls, must have a drink or side
    public boolean hasNonRiceItem() {

        // Original loop:
        // for (Price item : items) {
        //     if (item instanceof Drink || item instanceof Pastries) {
        //         return true;
        //     }
        // }
        // return false;

        return items.stream()
                .anyMatch(item -> item instanceof Drink || item instanceof Pastries);
    }

    // Builds the full ANSI-styled receipt for terminal display
    // Uses box drawing characters for borders and color codes for styling
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("\n");
        // Receipt header with restaurant name and slogan
        sb.append(GREEN).append("  ╔══════════════════════════════════════════╗\n").append(RESET);
        sb.append(GREEN).append("  ║").append(RESET).append(BG_GREEN).append(BLACK).append(BOLD).append("        DUBEM'S NAIJA KITCHEN            ").append(RESET).append(GREEN).append("║\n").append(RESET);
        sb.append(GREEN).append("  ║").append(RESET).append(WHITE).append("   \"Taste of Home, One Bowl at a Time\"   ").append(GREEN).append("║\n").append(RESET);
        sb.append(GREEN).append("  ╠══════════════════════════════════════════╣\n").append(RESET);
        sb.append(GREEN).append("  ║").append(RESET).append(YELLOW).append(BOLD).append("            ORDER RECEIPT                 ").append(RESET).append(GREEN).append("║\n").append(RESET);
        sb.append(GREEN).append("  ╠══════════════════════════════════════════╣\n").append(RESET);

        // List each item with its subtotal

        // Original loop:
        // int count = 1;
        // for (Price item : items) {  count++; }

         int[] count = {1};
        items.forEach(item -> {
            sb.append(GREEN).append("  ║ ").append(RESET);
            sb.append(WHITE).append(BOLD).append(count[0]).append(") ").append(RESET);
            sb.append(WHITE).append(item).append("\n");
            sb.append(GREEN).append("  ║ ").append(RESET);
            sb.append(CYAN).append(String.format("   Subtotal: $%.2f", item.getPrice())).append("\n").append(RESET);
            sb.append(GREEN).append("  ║").append(RESET).append(DIM).append("──────────────────────────────────────────").append(RESET).append("\n");
            count[0]++;
        });

        // Order total at the bottom
        sb.append(GREEN).append("  ╠══════════════════════════════════════════╣\n").append(RESET);
        sb.append(GREEN).append("  ║ ").append(RESET);
        sb.append(GREEN).append(BOLD).append(String.format("   ORDER TOTAL:  $%.2f", getTotal()));
        sb.append("                ").append(RESET).append("\n");
        sb.append(GREEN).append("  ╚══════════════════════════════════════════╝\n").append(RESET);

        return sb.toString();
    }
}