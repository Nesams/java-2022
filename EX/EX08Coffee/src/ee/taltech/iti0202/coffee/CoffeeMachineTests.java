package ee.taltech.iti0202.coffee;

import ee.taltech.iti0202.coffee.coffeeMachine.AutomaticMachine;
import ee.taltech.iti0202.coffee.coffeeMachine.CapsuleMachine;
import ee.taltech.iti0202.coffee.coffeeMachine.CoffeeMachine;
import ee.taltech.iti0202.coffee.coffeeMachine.CoffeeMachineBuilder;
import ee.taltech.iti0202.coffee.drinks.Drink;
import ee.taltech.iti0202.coffee.example.Main;
import ee.taltech.iti0202.coffee.exceptions.DrinkDoesNotExist;
import ee.taltech.iti0202.coffee.exceptions.MachineNeedsCare;
import ee.taltech.iti0202.coffee.exceptions.NotEnoughSupplies;
import ee.taltech.iti0202.coffee.kitchen.Kitchen;
import ee.taltech.iti0202.coffee.resources.Supplies;
import ee.taltech.iti0202.coffee.resources.WaterTank;
import org.junit.jupiter.api.Assertions;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CoffeeMachineTests {
    final int five = 5;
    final int ten = 10;
    final int six = 6;
    final int eight = 8;
    final int twelve = 12;
    final int thirtyFour = 34;

    List<Drink.Type> knownDrinks1 = List.of(Drink.Type.COFFEE, Drink.Type.CAPPUCCINO, Drink.Type.COCOA);
    List<Drink.Type> knownDrinks2 = List.of(Drink.Type.COFFEE, Drink.Type.CAPPUCCINO, Drink.Type.COCOA, Drink.Type.TEA);

    Drink coffee = new Drink(Drink.Type.COFFEE, 1, 2, 1, 1);
    Drink cappuccino = new Drink(Drink.Type.CAPPUCCINO, 1, 2, 2, 3);
    Drink cocoa = new Drink(Drink.Type.COCOA, 1, 3, 3, five);
    Drink tea = new Drink(Drink.Type.TEA, 1, 3, 1, 0);

    WaterTank waterTank = new WaterTank(six);
    Supplies supplies = new Supplies();

    CoffeeMachine coffeeMachine = new CoffeeMachine("CoffeeMachine", waterTank, supplies, knownDrinks2);

    CapsuleMachine capsuleMachine = new CoffeeMachineBuilder()
            .withName("CapsuleMachine")
            .withKnownDrinks(knownDrinks1)
            .withSupplies(supplies)
            .withTilTrashFull(ten)
            .withWaterTank(waterTank)
            .buildCapsuleMachine();

    AutomaticMachine automaticMachine = new CoffeeMachineBuilder()
            .withName("AutomaticMachine")
            .withSupplies(supplies)
            .withWaterTank(waterTank)
            .withTilTrashFull(five)
            .withKnownDrinks(knownDrinks2)
            .buildAutomaticMachine();

    Kitchen kitchen = new Kitchen("Kitchen");

    @org.junit.jupiter.api.Test
    void testIfAddingSuppliesWorks() {
        supplies.addSupplies("Coffee Beans", 3);
        supplies.addSupplies("Cocoa Powder", five);
        supplies.addSupplies("Sugar", five);
        supplies.addSupplies("Milk", ten);
        supplies.addSupplies("Tea", five);
        supplies.addSupplies("Coffee Capsule", 1);
        supplies.addSupplies("Cappuccino Capsule", 1);
        supplies.addSupplies("Cocoa Capsule", 1);

        Assertions.assertEquals(supplies.getSuppliesMap().size(), eight);
        Assertions.assertEquals(supplies.getSuppliesMap().get("Sugar"), five);
    }
    @org.junit.jupiter.api.Test
    void testIfCoffeeMachinesAreUsable() {
        assertTrue(coffeeMachine.usable());
        assertTrue(capsuleMachine.usable());
        assertTrue(automaticMachine.usable());
    }
    @org.junit.jupiter.api.Test
    void testIfMakingDrinksWord() throws MachineNeedsCare, DrinkDoesNotExist, NotEnoughSupplies {
        Main.main(null);
        supplies.addSupplies("Coffee Beans", 3);
        supplies.addSupplies("Cocoa Powder", five);
        supplies.addSupplies("Sugar", five);
        supplies.addSupplies("Milk", ten);
        supplies.addSupplies("Tea", five);
        supplies.addSupplies("Cocoa Capsule", 1);

        kitchen.addMachine(coffeeMachine);
        kitchen.addMachine(automaticMachine);
        kitchen.addMachine(capsuleMachine);

        kitchen.makeADrink(coffeeMachine, cappuccino);
        kitchen.makeADrink(automaticMachine, coffee);
        kitchen.makeADrink(capsuleMachine, cocoa);

        Assertions.assertEquals(kitchen.getMadeDrinks().size(), 3);
    }
    @org.junit.jupiter.api.Test
    void testIfCapsuleIsUsed() throws MachineNeedsCare, DrinkDoesNotExist, NotEnoughSupplies {
        supplies.addSupplies("Cappuccino Capsule", 1);
        capsuleMachine.start(cappuccino);

        assertFalse(capsuleMachine.getCapsule().isUsable());
    }
    @org.junit.jupiter.api.Test
    void testExceptions() throws MachineNeedsCare, DrinkDoesNotExist, NotEnoughSupplies {
        supplies.addSupplies("Coffee Beans", 1);
        supplies.addSupplies("Cocoa Powder", five);
        supplies.addSupplies("Sugar", twelve);
        supplies.addSupplies("Milk", ten);
        supplies.addSupplies("Tea", thirtyFour);
        String cause1 = null;
        String cause2 = null;
        String cause3 = null;

        kitchen.addMachine(coffeeMachine);
        kitchen.addMachine(capsuleMachine);

        try {
            kitchen.makeADrink(capsuleMachine, tea);
        } catch (DrinkDoesNotExist e) {
            cause1 = e.getReason();
        }
        Assertions.assertEquals(cause1, "No such capsule!");

        try {
            kitchen.makeADrink(coffeeMachine, coffee);
        } catch (NotEnoughSupplies e) {
            cause2 = e.getReason();
        }
        Assertions.assertEquals(cause2, "Not enough supplies for this drink!");

        try {
            kitchen.makeADrink(coffeeMachine, tea);
            kitchen.makeADrink(coffeeMachine, tea);
            kitchen.makeADrink(coffeeMachine, tea);
            kitchen.makeADrink(coffeeMachine, tea);
            kitchen.makeADrink(coffeeMachine, tea);
            kitchen.makeADrink(coffeeMachine, tea);
        } catch (MachineNeedsCare e) {
            cause3 = e.getReason();
        }
        Assertions.assertEquals(cause3, "Fill water tank or throw out trash!");
        assertTrue(waterTank.hasWater());
        coffeeMachine.throwOutTrash();
        assertTrue(coffeeMachine.usable());
    }

    @org.junit.jupiter.api.Test
    void hasEnoughSuppliesTest() {
        supplies.addSupplies("Cocoa Powder", six);
        supplies.addSupplies("Sugar", twelve);
        supplies.addSupplies("Milk", twelve);
        supplies.addSupplies("Tea", 2);
        supplies.addSupplies("Coffee Capsule", 3);
        supplies.takeCapsule(coffee);

        Assertions.assertTrue(supplies.hasEnoughSupplies(cocoa));
        Assertions.assertFalse(supplies.hasEnoughSupplies(tea));
        Assertions.assertTrue(supplies.hasEnoughCapsules(coffee));
    }
}
