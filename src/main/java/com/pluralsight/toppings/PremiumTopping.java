package com.pluralsight.toppings;


public abstract class PremiumTopping extends Topping {
    protected boolean extra;

    public PremiumTopping(String name) {
        super(name);
        this.extra = false;
    }

    public void setExtra(boolean extra) {
        this.extra = extra;
    }

    public boolean isExtra() {
        return extra;
    }
}
