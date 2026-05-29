package com.pluralsight.models;

import com.pluralsight.models.interfaces.Priceable;

// Pastry side items — Puff Puff, Meat Pie, or Egg Roll
// Implements Price so it can be stored in Order's List<Priceable>

public class Pastries implements Priceable {
    private String type;  // Puff Puff, Meat Pie, or Egg Roll

    // Constructor — sets the pastry type
    public Pastries(String type) {
        this.type = type;
    }

    // Getter
    public String getType() { return type; }

    // All pastries cost $4.99
    @Override
    public double getPrice() {
        return 4.99;
    }

    // Returns the type name for receipt display
    @Override
    public String toString() {
        return type;
    }
}