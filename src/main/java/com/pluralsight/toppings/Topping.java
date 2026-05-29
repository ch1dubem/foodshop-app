package com.pluralsight.toppings;



// Abstract base class for all topping types
// Cannot be instantiated directly — must use a subclass
// RegularTopping, SauceTopping, and PremiumTopping all extend this
public abstract class Topping {
    // Protected so child classes can access it directly
    protected String name;

    // Constructor — called by child classes using super(name)
    public Topping(String name) {
        this.name = name;
    }

    // Returns the topping name (e.g. "Chicken", "Plantain")
    public String getName() {
        return name;
    }

    // Abstract — each subclass calculates price differently
    // Takes riceSize because premium topping prices vary by bowl size
    public abstract double getPrice(String riceSize);
}
