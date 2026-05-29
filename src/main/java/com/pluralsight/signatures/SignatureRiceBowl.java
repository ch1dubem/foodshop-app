package com.pluralsight.signatures;

import com.pluralsight.models.Rice;
import com.pluralsight.toppings.*;
import java.util.ArrayList;

// Factory class that creates pre-built signature rice bowls
// Customers can select a signature bowl and optionally customize it
public class SignatureRiceBowl {

    // Creates and returns 3 pre-built Rice bowls with toppings already added
    public static ArrayList<Rice> getSignatureBowls() {
        ArrayList<Rice> bowls = new ArrayList<>();

        // The Jollof Special — Large Jollof with Chicken, Plantain, Coleslaw, Peppered Stew
        Rice jollofSpecial = new Rice("Large", "Jollof");
        jollofSpecial.addTopping(new MeatTopping("Chicken"));
        jollofSpecial.addTopping(new RegularTopping("Plantain"));
        jollofSpecial.addTopping(new RegularTopping("Coleslaw"));
        jollofSpecial.addTopping(new SauceTopping("Peppered Stew"));
        jollofSpecial.setIsSpicy(true);
        bowls.add(jollofSpecial);

        // Fish Lover's Bowl — Medium Fried with Tilapia, Catfish, Eggs, Ofada Stew
        Rice fishLover = new Rice("Medium", "Fried");
        fishLover.addTopping(new FishTopping("Tilapia"));
        fishLover.addTopping(new FishTopping("Catfish"));
        fishLover.addTopping(new RegularTopping("Eggs"));
        fishLover.addTopping(new SauceTopping("Ofada Stew"));
        fishLover.setIsSpicy(false);
        bowls.add(fishLover);

        // The Lagos Classic — Large Coconut with Beef, Goat Meat, Beans, Plantain, Efe Riro
        Rice lagosClassic = new Rice("Large", "Coconut");
        lagosClassic.addTopping(new MeatTopping("Beef"));
        lagosClassic.addTopping(new MeatTopping("Goat Meat"));
        lagosClassic.addTopping(new RegularTopping("Beans"));
        lagosClassic.addTopping(new RegularTopping("Plantain"));
        lagosClassic.addTopping(new SauceTopping("Efe Riro"));
        lagosClassic.setIsSpicy(true);
        bowls.add(lagosClassic);

        return bowls;
    }

    // Returns display names for the signature bowls

    public static String[] getSignatureNames() {
        return new String[] {
                "The Jollof Special",
                "Fish Lover's Bowl",
                "The Lagos Classic"
        };
    }
}