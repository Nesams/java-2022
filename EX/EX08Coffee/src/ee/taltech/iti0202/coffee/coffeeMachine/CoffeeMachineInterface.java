package ee.taltech.iti0202.coffee.coffeeMachine;

import ee.taltech.iti0202.coffee.drinks.Drink;
import ee.taltech.iti0202.coffee.exceptions.DrinkDoesNotExist;
import ee.taltech.iti0202.coffee.exceptions.MachineNeedsCare;
import ee.taltech.iti0202.coffee.exceptions.NotEnoughSupplies;
import ee.taltech.iti0202.coffee.exceptions.OutOfSupplies;

import java.util.Optional;

public interface CoffeeMachineInterface {

    Optional<Drink> start(Drink drink) throws NotEnoughSupplies,
            MachineNeedsCare, OutOfSupplies, DrinkDoesNotExist;

    boolean usable();
}
