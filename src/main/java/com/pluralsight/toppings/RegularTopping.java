package com.pluralsight.toppings;


// Concrete class for free toppings: Plantain, Beans, Eggs, Coleslaw, Seaweed
// Extends Topping and always returns 0.0 for price
public class RegularTopping extends Topping {

    // Calls parent constructor to set the name
    public RegularTopping(String name) {
        super(name);
    }

    // Regular toppings are free regardless of rice size
    @Override
    public double getPrice(String riceSize) {
        return 0.0;
    }

    // Returns the name
    @Override
    public String toString() {
        return name;
    }
}
