package com.pluralsight.signatures;



import com.pluralsight.models.Rice;
import com.pluralsight.toppings.FishTopping;
import com.pluralsight.toppings.MeatTopping;
import com.pluralsight.toppings.RegularTopping;
import com.pluralsight.toppings.SauceTopping;

import java.util.ArrayList;

public class SignatureRiceBowl {

    public static ArrayList<Rice> getSignatureBowls() {
        ArrayList<Rice> bowls = new ArrayList<>();

        Rice jollofSpecial = new Rice("Large", "Jollof");
        jollofSpecial.addTopping(new MeatTopping("Chicken"));
        jollofSpecial.addTopping(new RegularTopping("Plantain"));
        jollofSpecial.addTopping(new RegularTopping("Coleslaw"));
        jollofSpecial.addTopping(new SauceTopping("Peppered Stew"));
        jollofSpecial.setIsSpicy(true);
        bowls.add(jollofSpecial);

        Rice fishLover = new Rice("Medium", "Fried");
        fishLover.addTopping(new FishTopping("Tilapia"));
        fishLover.addTopping(new FishTopping("Catfish"));
        fishLover.addTopping(new RegularTopping("Eggs"));
        fishLover.addTopping(new SauceTopping("Ofada Stew"));
        fishLover.setIsSpicy(false);
        bowls.add(fishLover);

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

    public static String[] getSignatureNames() {
        return new String[] {
                "The Jollof Special",
                "Fish Lover's Bowl",
                "The Lagos Classic"
        };
    }
}
