package ee.taltech.iti0202.coffee.example;

import ee.taltech.iti0202.coffee.coffeeMachine.AutomaticMachine;
import ee.taltech.iti0202.coffee.coffeeMachine.CapsuleMachine;
import ee.taltech.iti0202.coffee.coffeeMachine.CoffeeMachine;
import ee.taltech.iti0202.coffee.coffeeMachine.CoffeeMachineBuilder;
import ee.taltech.iti0202.coffee.drinks.Drink;
import ee.taltech.iti0202.coffee.exceptions.DrinkDoesNotExist;
import ee.taltech.iti0202.coffee.exceptions.MachineNeedsCare;
import ee.taltech.iti0202.coffee.exceptions.NotEnoughSupplies;
import ee.taltech.iti0202.coffee.kitchen.Kitchen;
import ee.taltech.iti0202.coffee.resources.Supplies;
import ee.taltech.iti0202.coffee.resources.WaterTank;

import java.util.List;

public class Main {

    public static void main(String[] args) throws NotEnoughSupplies, MachineNeedsCare,
            DrinkDoesNotExist {

        final int six = 6;
        final int five = 5;
        final int ten = 10;
        List<Drink.Type> drinkTypes1 = List.of(Drink.Type.COFFEE, Drink.Type.CAPPUCCINO,
                Drink.Type.COCOA);
        List<Drink.Type> drinkTypes2 = List.of(Drink.Type.COFFEE, Drink.Type.CAPPUCCINO,
                Drink.Type.COCOA, Drink.Type.TEA);

        Drink coffee = new Drink(Drink.Type.COFFEE, 1, 2, 1, 1);
        Drink cappuccino = new Drink(Drink.Type.CAPPUCCINO, 1, 2, 2, 3);
        Drink cocoa = new Drink(Drink.Type.COCOA, 1, 3, 3, five);
        Drink tea = new Drink(Drink.Type.TEA, 1, 3, 1, 0);

        WaterTank waterTank = new WaterTank(six);

        Kitchen kitchen = new Kitchen("My kitchen");

        Supplies supplies = new Supplies();

        supplies.addSupplies("Coffee Beans", 3);
        supplies.addSupplies("Cocoa Powder", five);
        supplies.addSupplies("Sugar", five);
        supplies.addSupplies("Milk", ten);
        supplies.addSupplies("Tea", five);
        supplies.addSupplies("Coffee Capsule", 1);
        supplies.addSupplies("Cappuccino Capsule", 1);
        supplies.addSupplies("Cocoa Capsule", 1);

        CoffeeMachine coffeeMachine = new CoffeeMachineBuilder()
                .withName("CoffeeMachine")
                .withSupplies(supplies)
                .withWaterTank(waterTank)
                .withTilTrashFull(five)
                .withKnownDrinks(drinkTypes2)
                .buildCoffeeMachine();

        CapsuleMachine capsuleMachine = new CoffeeMachineBuilder()
                .withName("CapsuleMachine")
                .withKnownDrinks(drinkTypes1)
                .withSupplies(supplies)
                .withTilTrashFull(ten)
                .withWaterTank(waterTank)
                .buildCapsuleMachine();

        AutomaticMachine automaticMachine = new CoffeeMachineBuilder()
                .withName("AutomaticMachine")
                .withSupplies(supplies)
                .withWaterTank(waterTank)
                .withTilTrashFull(five)
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
        supplies.addSupplies("Sugar", five);
        kitchen.makeADrink(coffeeMachine, tea);
    }
}
