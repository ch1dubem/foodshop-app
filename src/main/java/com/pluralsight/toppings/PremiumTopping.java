package com.pluralsight.toppings;

// Abstract class sitting between Topping and MeatTopping/FishTopping
// Holds the "extra" logic — only premium toppings can have extra

public abstract class PremiumTopping extends Topping {
    // Whether the customer wants extra of this topping
    // When true, an additional charge is added to the base price
    protected boolean extra;

    // Calls parent constructor, sets extra to false by default
    public PremiumTopping(String name) {
        super(name);
        this.extra = false;
    }

    // Allows the UI to set extra to true when customer requests it
    public void setExtra(boolean extra) {
        this.extra = extra;
    }

    // Returns whether extra was requested
    public boolean isExtra() {
        return extra;
    }
}
