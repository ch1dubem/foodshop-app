package com.pluralsight.toppings;

// Concrete class for premium fish toppings: Tilapia, Catfish, Croaker, Red Snapper
public class FishTopping extends PremiumTopping {

    // Calls PremiumTopping constructor which chains up to Topping
    public FishTopping(String name) {
        super(name);
    }

    // Calculates price based on rice bowl size
    @Override
    public double getPrice(String riceSize) {
        double base;
        double extraCost;

        switch (riceSize.toLowerCase()) {
            case "medium" -> { base = 3.50; extraCost = 1.75; }
            case "large" -> { base = 4.50; extraCost = 2.25; }
            default -> { base = 2.50; extraCost = 1.25; }
        }

        //  if extra is true, add extraCost to base; otherwise just base
        return extra ? base + extraCost : base;
    }

    // Shows "Tilapia" or "Tilapia (extra)" on the receipt
    @Override
    public String toString() {
        return extra ? name + " (extra)" : name;
    }
}