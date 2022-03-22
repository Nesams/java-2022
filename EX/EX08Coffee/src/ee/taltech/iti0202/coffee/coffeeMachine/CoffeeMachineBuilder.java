package ee.taltech.iti0202.coffee.coffeeMachine;

import ee.taltech.iti0202.coffee.drinks.Drink;
import ee.taltech.iti0202.coffee.resources.Supplies;
import ee.taltech.iti0202.coffee.resources.WaterTank;

import java.util.List;

public class CoffeeMachineBuilder {
    private List<Drink.Type> knownDrinks;
    private String name;
    private int tilTrashFull;
    private WaterTank waterTank;
    private Supplies supplies;

    public CoffeeMachineBuilder withName(String name) {
        this.name = name;
        return this;
    }
    public CoffeeMachineBuilder withTilTrashFull(int tilTrashFull) {
        this.tilTrashFull = tilTrashFull;
        return this;
    }
    public CoffeeMachineBuilder withWaterTank(WaterTank waterTank) {
        this.waterTank = waterTank;
        return this;
    }
    public CoffeeMachineBuilder withSupplies(Supplies supplies) {
        this.supplies = supplies;
        return this;
    }
    public CoffeeMachineBuilder withKnownDrinks(List<Drink.Type> knownDrinks) {
        this.knownDrinks = knownDrinks;
        return this;
    }
    public CoffeeMachine buildCoffeeMachine() {
        return new CoffeeMachine(
                this.name,
                this.waterTank,
                this.supplies,
                this.knownDrinks);
    }
    public CapsuleMachine buildCapsuleMachine() {
        return new CapsuleMachine(
                this.name,
                this.waterTank,
                this.supplies,
                this.knownDrinks);
    }
    public AutomaticMachine buildAutomaticMachine() {
        return new AutomaticMachine(
                this.name,
                this.waterTank,
                this.supplies,
                this.knownDrinks);
    }
}
