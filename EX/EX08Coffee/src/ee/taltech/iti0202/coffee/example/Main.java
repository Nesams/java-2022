package ee.taltech.iti0202.coffee.example;

import ee.taltech.iti0202.coffee.coffeeMachine.AutomaticMachine;
import ee.taltech.iti0202.coffee.coffeeMachine.CapsuleMachine;
import ee.taltech.iti0202.coffee.coffeeMachine.CoffeeMachine;
import ee.taltech.iti0202.coffee.coffeeMachine.CoffeeMachineBuilder;
import ee.taltech.iti0202.coffee.drinks.Drink;
import ee.taltech.iti0202.coffee.exceptions.DrinkDoesNotExist;
import ee.taltech.iti0202.coffee.exceptions.MachineNeedsCare;
import ee.taltech.iti0202.coffee.exceptions.NotEnoughSupplies;
import ee.taltech.iti0202.coffee.exceptions.OutOfSupplies;
import ee.taltech.iti0202.coffee.kitchen.Kitchen;
import ee.taltech.iti0202.coffee.resources.Supplies;
import ee.taltech.iti0202.coffee.resources.WaterTank;

import java.util.List;

public class Main {

    public static void main(String[] args) throws NotEnoughSupplies, MachineNeedsCare, DrinkDoesNotExist, OutOfSupplies {

        List<Drink.Type> drinkTypes1 = List.of(Drink.Type.COFFEE, Drink.Type.CAPPUCCINO,
                Drink.Type.COCOA);
        List<Drink.Type> drinkTypes2 = List.of(Drink.Type.COFFEE, Drink.Type.CAPPUCCINO,
                Drink.Type.COCOA, Drink.Type.TEA);

        Drink coffee = new Drink(Drink.Type.COFFEE, 1, 2, 1, 1);
        Drink cappuccino = new Drink(Drink.Type.CAPPUCCINO, 1, 2, 2, 3);
        Drink cocoa = new Drink(Drink.Type.COCOA, 1, 3, 3, 5);
        Drink tea = new Drink(Drink.Type.TEA, 1, 3, 1, 0);

        WaterTank waterTank = new WaterTank(6);

        Kitchen kitchen = new Kitchen("My kitchen");

        Supplies supplies = new Supplies();

        supplies.addSupplies("Coffee Powder", 3);
        supplies.addSupplies("Cocoa Powder", 5);
        supplies.addSupplies("Sugar", 5);
        supplies.addSupplies("Milk", 10);
        supplies.addSupplies("Tea", 5);
        supplies.addSupplies("Coffee Capsule", 1);
        supplies.addSupplies("Cappuccino Capsule", 1);
        supplies.addSupplies("Cocoa Capsule", 1);

        CoffeeMachine coffeeMachine = new CoffeeMachineBuilder()
                .withName("CoffeeMachine")
                .withSupplies(supplies)
                .withWaterTank(waterTank)
                .withTilTrashFull(5)
                .withKnownDrinks(drinkTypes2)
                .buildCoffeeMachine();

        CapsuleMachine capsuleMachine = new CoffeeMachineBuilder()
                .withName("CapsuleMachine")
                .withKnownDrinks(drinkTypes1)
                .withSupplies(supplies)
                .withTilTrashFull(10)
                .withWaterTank(waterTank)
                .buildCapsuleMachine();

        AutomaticMachine automaticMachine = new CoffeeMachineBuilder()
                .withName("AutomaticMachine")
                .withSupplies(supplies)
                .withWaterTank(waterTank)
                .withTilTrashFull(5)
                .withKnownDrinks(drinkTypes2)
                .buildAutomaticMachine();

        kitchen.addMachine(coffeeMachine);
        kitchen.addMachine(automaticMachine);
        kitchen.addMachine(capsuleMachine);

        kitchen.makeADrink(automaticMachine, cappuccino);
        kitchen.makeADrink(automaticMachine, coffee);
        kitchen.makeADrink(capsuleMachine, cocoa);
        //kitchen.makeADrink(capsuleMachine, cocoa);
        waterTank.fill();
        supplies.addSupplies("Sugar", 5);
        kitchen.makeADrink(coffeeMachine, tea);
    }
}
