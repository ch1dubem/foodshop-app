package com.pluralsight.toppings;

// Concrete class for free stew/condiment toppings: Ofada, Peppered, Ofe Akwu, Efe Riro
// Same structure as RegularTopping — separate class for menu categorization
public class SauceTopping extends Topping {

    // Calls parent constructor to set the name
    public SauceTopping(String name) {
        super(name);
    }

    // Stew condiments are free regardless of rice size
    @Override
    public double getPrice(String riceSize) {
        return 0.0;
    }

    // Returns the name for display
    @Override
    public String toString() {
        return name;
    }
}
