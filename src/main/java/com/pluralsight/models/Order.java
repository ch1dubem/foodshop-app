package com.pluralsight.models;



import com.pluralsight.models.interfaces.Priceable;

import java.util.ArrayList;
import java.util.List;

public class Order {
    private List<Priceable> items;

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
        double total = 0;
        for (Priceable item : items) {
            total += item.getPrice();
        }
        return total;
    }

    public boolean isEmpty() {
        return items.isEmpty();
    }

    public boolean hasNonRiceItem() {
        for (Priceable item : items) {
            if (item instanceof Drink || item instanceof Pastries) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("==========================================\n");
        sb.append("        DUBEM'S NAIJA KITCHEN\n");
        sb.append("   \"Taste of Home, One Bowl at a Time\"\n");
        sb.append("==========================================\n\n");

        int count = 1;
        for (Priceable item : items) {
            sb.append(String.format("%d) %s\n", count, item));
            sb.append(String.format("   Subtotal: $%.2f\n\n", item.getPrice()));
            count++;
        }

        sb.append("------------------------------------------\n");
        sb.append(String.format("ORDER TOTAL: $%.2f\n", getTotal()));
        sb.append("==========================================");

        return sb.toString();
    }
}
