package com.pluralsight.toppings;

public class SauceTopping extends Topping {

    public SauceTopping(String name) {
        super(name);
    }

    @Override
    public double getPrice(String riceSize) {
        return 0.0;
    }

    @Override
    public String toString() {
        return name;
    }
}
