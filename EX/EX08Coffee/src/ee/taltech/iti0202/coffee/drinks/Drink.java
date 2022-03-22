package ee.taltech.iti0202.coffee.drinks;

public class Drink {

    private Type type;
    private int neededWater;
    private int neededPowder;
    private int neededSugar;
    private int neededMilk;
    public enum Type {
        COFFEE, CAPPUCCINO, COCOA, TEA
    }
    public Drink(Type type, int neededWater, int neededPowder, int neededSugar, int neededMilk) {
        this.type = type;
        this.neededWater = neededWater;
        this.neededPowder = neededPowder;
        this.neededSugar = neededSugar;
        this.neededMilk = neededMilk;
    }

    public Type getType() {
        return type;
    }

    public int getNeededWater() {
        return neededWater;
    }

    public int getNeededPowder() {
        return neededPowder;
    }

    public int getNeededSugar() {
        return neededSugar;
    }

    public int getNeededMilk() {
        return neededMilk;
    }
}
