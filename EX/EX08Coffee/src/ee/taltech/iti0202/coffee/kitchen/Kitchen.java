package ee.taltech.iti0202.coffee.kitchen;

import ee.taltech.iti0202.coffee.coffeeMachine.CoffeeMachineInterface;
import ee.taltech.iti0202.coffee.drinks.Drink;
import ee.taltech.iti0202.coffee.exceptions.DrinkDoesNotExist;
import ee.taltech.iti0202.coffee.exceptions.MachineNeedsCare;
import ee.taltech.iti0202.coffee.exceptions.NotEnoughSupplies;

import java.lang.constant.Constable;
import java.util.ArrayList;

public class Kitchen {
    private String name;
    private ArrayList<CoffeeMachineInterface> coffeeMachines;
    private ArrayList<Drink> made_drinks;

    public Kitchen(String name) {
        this.name = name;
        this.coffeeMachines = new ArrayList<>();
        this.made_drinks = new ArrayList<>();
    }

    public ArrayList<CoffeeMachineInterface> getCoffeeMachines() {
        return coffeeMachines;
    }

    public ArrayList<Drink> getMade_drinks() {
        return made_drinks;
    }

    public void addMachine(CoffeeMachineInterface machine) {
        this.coffeeMachines.add(machine);
    }
    public Constable makeADrink(CoffeeMachineInterface machine, Drink drink) throws MachineNeedsCare, DrinkDoesNotExist,
            NotEnoughSupplies {
        if (coffeeMachines.contains(machine)) {
            machine.start(drink);
            made_drinks.add(drink);
        }
        return "Could not make a drink";
    }
}
