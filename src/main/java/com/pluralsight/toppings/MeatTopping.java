package com.pluralsight.toppings;

// Concrete class for meat toppings: Chicken, Turkey, Goat Meat, Beef, Lamb
// Extends PremiumTopping — inherits the extra boolean
public class MeatTopping extends PremiumTopping {

    // Calls PremiumTopping constructor which chains up to Topping
    public MeatTopping(String name) {
        super(name);
    }

    // Calculates price based on rice bowl size
    @Override
    public double getPrice(String riceSize) {
        double base;
        double extraCost;

        switch (riceSize.toLowerCase()) {
            case "medium" -> { base = 3.00; extraCost = 1.50; }
            case "large" -> { base = 4.00; extraCost = 2.00; }
            default -> { base = 2.00; extraCost = 1.00; }
        }

        // if extra is true, add extraCost to base; otherwise just base
        return extra ? base + extraCost : base;
    }

    // Shows "Chicken" or "Chicken (extra)" on the receipt
    @Override
    public String toString() {
        return extra ? name + " (extra)" : name;
    }
}