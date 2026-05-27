package com.pluralsight.toppings;

public class RegularTopping extends Topping {

    public RegularTopping(String name) {
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
