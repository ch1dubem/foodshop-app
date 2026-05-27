package com.pluralsight.toppings;


public class MeatTopping extends PremiumTopping {

    public MeatTopping(String name) {
        super(name);
    }

    @Override
    public double getPrice(String riceSize) {
        double base;
        double extraCost;

        switch (riceSize.toLowerCase()) {
            case "medium" -> { base = 3.00; extraCost = 1.50; }
            case "large" -> { base = 4.00; extraCost = 2.00; }
            default -> { base = 2.00; extraCost = 1.00; }
        }

        return extra ? base + extraCost : base;
    }

    @Override
    public String toString() {
        return extra ? name + " (extra)" : name;
    }
}
