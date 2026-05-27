package com.pluralsight.models;


import com.pluralsight.models.interfaces.Priceable;

public class Drink implements Priceable {
    private String size;
    private String flavor;

    public Drink(String size, String flavor) {
        this.size = size;
        this.flavor = flavor;
    }

    public String getSize() { return size; }
    public String getFlavor() { return flavor; }

    @Override
    public double getPrice() {
        switch (size.toLowerCase()) {
            case "medium" -> { return 4.00; }
            case "large" -> { return 5.00; }
            default -> { return 3.00; }
        }
    }

    @Override
    public String toString() {
        return String.format("%s %s", size, flavor);
    }
}
