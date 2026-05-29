package com.pluralsight.models;

import com.pluralsight.models.interfaces.Priceable;
import com.pluralsight.toppings.Topping;

import java.util.ArrayList;
import java.util.List;

public class Rice implements Priceable {

    private String size;
    private String riceType;
    private List<Topping> toppings;
    private boolean isSpicy;


    public Rice(String size, String riceType) {
        this.size = size;
        this.riceType = riceType;
        this.toppings = new ArrayList<>();
        this.isSpicy = false;
    }

    public String getSize() { return size; }
    public String getRiceType() { return riceType; }
    public boolean isSpicy() { return isSpicy; }
    public List<Topping> getToppings() { return toppings; }


    public void addTopping(Topping topping) {
        this.toppings.add(topping);

    }

    public void removeTopping(String name) {

        for (int i = toppings.size() - 1; i >= 0; i--) {
            if (toppings.get(i).getName().equalsIgnoreCase(name)) {
                toppings.remove(i);
            }
        }
    }

    public void setIsSpicy(boolean isSpicy) { this.isSpicy = isSpicy; }








    @Override
    public double getPrice() {
        double base;
        switch (size.toLowerCase()) {
            case "medium" -> base = 10.99;
            case "large" -> base = 13.99;
            default -> base = 7.99;
        }

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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("%s %s Rice%s", size, riceType, isSpicy ? " (Spicy)" : ""));

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
