package ee.taltech.iti0202.coffee.capsules;

import ee.taltech.iti0202.coffee.drinks.Drink;

public class Capsule {
    private final Drink.Type type;
    private int timesUsed;

    public Capsule(Drink.Type type) {
        this.type = type;
        this.timesUsed = 0;
    }

    public Drink.Type getType() {
        return type;
    }

    public void useCapsule() {
        this.timesUsed = 1;
    }

    public boolean isUsable() {
        return this.timesUsed == 1;
    }
}
