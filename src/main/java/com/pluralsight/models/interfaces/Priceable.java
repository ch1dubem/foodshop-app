package com.pluralsight.models.interfaces;



// Interface that defines a contract for all orderable items
// Any class implementing Price must provide a getPrice() method
// This allows Rice, Drink, and Pastries to be stored in one List<Price>
public interface Priceable {
    // Returns the total price of the item
    double getPrice();
}

