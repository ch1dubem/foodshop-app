package com.pluralsight.models;


import com.pluralsight.models.interfaces.Priceable;

public class Pastries implements Priceable {
    private String type;

    public Pastries(String type) {
        this.type = type;
    }

    public String getType() { return type; }

    @Override
    public double getPrice() {
        return 4.99;
    }

    @Override
    public String toString() {
        return type;
    }
}
