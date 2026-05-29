package com.pluralsight.models;

import com.pluralsight.models.interfaces.Priceable;
import com.pluralsight.toppings.Topping;
import java.util.ArrayList;
import java.util.List;

// The main item of the shop — a customizable rice bowl
// Implements Price so it can be stored in Order's List<Priceable>
// Holds a list of toppings that contribute to the total price

public class Rice implements Priceable {
    private String size;       // Small, Medium, or Large
    private String riceType;   // Jollof, Fried, Coconut, or White
    private List<Topping> toppings;  // All toppings added to this bowl
    private boolean isSpicy;   // Whether the customer wants it spicy

    // Constructor — sets size and type, initializes empty topping list
    public Rice(String size, String riceType) {
        this.size = size;
        this.riceType = riceType;
        this.toppings = new ArrayList<>();
        this.isSpicy = false;
    }

    // Getters
    public String getSize() { return size; }
    public String getRiceType() { return riceType; }
    public boolean isSpicy() { return isSpicy; }
    public List<Topping> getToppings() { return toppings; }

    // Adds any type of topping — polymorphism handles Meat, Fish, Regular, Sauce
    public void addTopping(Topping topping) {
        toppings.add(topping);
    }

    // Removes topping by name using lambda — used when customizing signature bowls
    public void removeTopping(String name) {
        // Original loop:
        // for (int i = toppings.size() - 1; i >= 0; i--) {
        //     if (toppings.get(i).getName().equalsIgnoreCase(name)) {
        //         toppings.remove(i);
        //     }
        // }
        toppings.removeIf(t -> t.getName().equalsIgnoreCase(name));
    }

    // Sets spicy flag
    public void setIsSpicy(boolean isSpicy) {
        this.isSpicy = isSpicy;
    }

    // Calculates total price: base rice price + all topping prices
    // Each topping's getPrice() receives this bowl's size so pricing scales correctly
    @Override
    public double getPrice() {
        // Base price depends on size
        double base;
        switch (size.toLowerCase()) {
            case "small" -> base = 7.99;
            case "medium" -> base = 10.99;
            case "large" -> base = 13.99;
            default -> base = 7.99;
        }

        // Sum all topping prices using stream — each topping calculates its own price

        // Original loop:
        // double toppingTotal = 0;
        // for (Topping t : toppings) {
        //     toppingTotal += t.getPrice(size);
        // }
        double toppingTotal = toppings.stream()
                .mapToDouble(t -> t.getPrice(size))
                .sum();

        return base + toppingTotal;
    }

    // Builds formatted string for receipt display
    // Shows rice type, size, spicy flag, and each topping with its price
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("%s %s Rice%s", size, riceType, isSpicy ? " (Spicy)" : ""));

        // Display each topping — shows price for premium, "included" for free

        // Original loop:
        // for (Topping t : toppings) {
        //     double price = t.getPrice(size);
        //     if (price > 0) {
        //         sb.append(String.format("\n   + %-25s $%.2f", t, price));
        //     } else {
        //         sb.append(String.format("\n   + %-25s included", t));
        //     }
        // }
        toppings.forEach(t -> {
            double price = t.getPrice(size);
            if (price > 0) {
                sb.append(String.format("\n   + %-25s $%.2f", t, price));
            } else {
                sb.append(String.format("\n   + %-25s included", t));
            }
        });

        return sb.toString();
    }
}