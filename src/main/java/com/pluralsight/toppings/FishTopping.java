package com.pluralsight.toppings;

public class FishTopping extends PremiumTopping {

    public FishTopping(String name) {
        super(name);
    }

    @Override
    public double getPrice(String riceSize) {
        double base;
        double extraCost;

        switch (riceSize.toLowerCase()) {
            case "medium" -> { base = 3.50; extraCost = 1.75; }
            case "large" -> { base = 4.50; extraCost = 2.25; }
            default -> { base = 2.50; extraCost = 1.25; }
        }

        return extra ? base + extraCost : base;
    }

    @Override
    public String toString() {
        return extra ? name + " (extra)" : name;
    }
}
