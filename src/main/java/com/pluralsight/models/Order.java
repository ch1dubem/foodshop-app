package com.pluralsight.models;

import com.pluralsight.models.interfaces.Priceable;

import java.util.ArrayList;
import java.util.List;

public class Order {
    public List<Priceable> items;

    private static final String RESET = "\033[0m";
    private static final String BOLD = "\033[1m";
    private static final String GREEN = "\033[32m";
    private static final String YELLOW = "\033[33m";
    private static final String CYAN = "\033[36m";
    private static final String WHITE = "\033[37m";
    private static final String DIM = "\033[2m";
    private static final String BG_GREEN = "\033[42m";
    private static final String BLACK = "\033[30m";

    public Order() {
        this.items = new ArrayList<>();
    }

    public void addItem(Priceable item) {
        items.add(item);
    }

    public void removeItem(int index) {
        if (index >= 0 && index < items.size()) {
            items.remove(index);
        }
    }

    public List<Priceable> getItems() {
        return items;
    }

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

    public boolean isEmpty() {
        return items.isEmpty();
    }

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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("\n");
        sb.append(GREEN).append("  ╔══════════════════════════════════════════╗\n").append(RESET);
        sb.append(GREEN).append("  ║").append(RESET).append(BG_GREEN).append(BLACK).append(BOLD).append("        DUBEM'S NAIJA KITCHEN            ").append(RESET).append(GREEN).append("║\n").append(RESET);
        sb.append(GREEN).append("  ║").append(RESET).append(WHITE).append("   \"Taste of Home, One Bowl at a Time\"   ").append(GREEN).append("║\n").append(RESET);
        sb.append(GREEN).append("  ╠══════════════════════════════════════════╣\n").append(RESET);
        sb.append(GREEN).append("  ║").append(RESET).append(YELLOW).append(BOLD).append("            ORDER RECEIPT                 ").append(RESET).append(GREEN).append("║\n").append(RESET);
        sb.append(GREEN).append("  ╠══════════════════════════════════════════╣\n").append(RESET);

        // Original loop:
        // int count = 1;
        // for (Price item : items) {
        //     sb.append("  ║ ").append(count).append(") ").append(item).append("\n");
        //     sb.append("  ║ ").append(String.format("   Subtotal: $%.2f", item.getPrice())).append("\n");
        //     sb.append("  ║──────────────────────────────────────────\n");
        //     count++;
        // }
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

        sb.append(GREEN).append("  ╠══════════════════════════════════════════╣\n").append(RESET);
        sb.append(GREEN).append("  ║ ").append(RESET);
        sb.append(GREEN).append(BOLD).append(String.format("   ORDER TOTAL:  $%.2f", getTotal()));
        sb.append("                ").append(RESET).append("\n");
        sb.append(GREEN).append("  ╚══════════════════════════════════════════╝\n").append(RESET);

        return sb.toString();
    }
}