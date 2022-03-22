package ee.taltech.iti0202.coffee.kitchen;

import ee.taltech.iti0202.coffee.coffeeMachine.CoffeeMachineInterface;
import ee.taltech.iti0202.coffee.drinks.Drink;
import ee.taltech.iti0202.coffee.exceptions.DrinkDoesNotExist;
import ee.taltech.iti0202.coffee.exceptions.MachineNeedsCare;
import ee.taltech.iti0202.coffee.exceptions.NotEnoughSupplies;
import ee.taltech.iti0202.coffee.exceptions.OutOfSupplies;

import java.lang.constant.Constable;
import java.util.ArrayList;

public class Kitchen {
    private String name;
    private ArrayList<CoffeeMachineInterface> coffeeMachines;

    public Kitchen(String name) {
        this.name = name;
        this.coffeeMachines = new ArrayList<>();
    }
    public void addMachine(CoffeeMachineInterface machine) {
        this.coffeeMachines.add(machine);
    }
    public Constable makeADrink(CoffeeMachineInterface machine, Drink drink) throws MachineNeedsCare, DrinkDoesNotExist,
            NotEnoughSupplies, OutOfSupplies {
        if (coffeeMachines.contains(machine)) {
            machine.start(drink);
        }
        return "Could not make a drink";
    }
}
