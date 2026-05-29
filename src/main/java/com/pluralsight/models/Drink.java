package com.pluralsight.models;

import com.pluralsight.models.interfaces.Priceable;

// Drink class — Zobo or Chapman in Small, Medium, or Large
// Implements Price so it can be stored in Order's List<Priceable>
public class Drink implements Priceable {
    private String size;    // Small, Medium, or Large
    private String flavor;  // Zobo or Chapman

    // Constructor — sets size and flavor
    public Drink(String size, String flavor) {
        this.size = size;
        this.flavor = flavor;
    }

    // Getters
    public String getSize() { return size; }
    public String getFlavor() { return flavor; }

    // Returns price based on size: Small $3.00, Medium $4.00, Large $5.00
    @Override
    public double getPrice() {
        switch (size.toLowerCase()) {
            case "small" -> { return 3.00; }
            case "medium" -> { return 4.00; }
            case "large" -> { return 5.00; }
            default -> { return 3.00; }
        }
    }

    // Shows "Medium Zobo" or "Large Chapman" on receipt
    @Override
    public String toString() {
        return String.format("%s %s", size, flavor);
    }
}