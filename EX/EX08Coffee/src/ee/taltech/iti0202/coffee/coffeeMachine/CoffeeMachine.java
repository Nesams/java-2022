package ee.taltech.iti0202.coffee.coffeeMachine;

import ee.taltech.iti0202.coffee.drinks.Drink;
import ee.taltech.iti0202.coffee.exceptions.DrinkDoesNotExist;
import ee.taltech.iti0202.coffee.exceptions.MachineNeedsCare;
import ee.taltech.iti0202.coffee.exceptions.NotEnoughSupplies;
import ee.taltech.iti0202.coffee.resources.Supplies;
import ee.taltech.iti0202.coffee.resources.WaterTank;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

public class CoffeeMachine implements CoffeeMachineInterface {

    List<Drink.Type> knownDrinks;
    String name;
    int tilTrashFull;
    WaterTank waterTank;
    Supplies supplies;
    private ArrayList<Drink> madeDrinks;
    Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    public CoffeeMachine(String name, WaterTank waterTank,
                         Supplies supplies, List<Drink.Type> knownDrinks) {
        this.name = name;
        this.tilTrashFull = 5;
        this.waterTank = waterTank;
        this.supplies = supplies;
        this.knownDrinks = knownDrinks;
        this.madeDrinks = new ArrayList<>();
    }
    public void throwOutTrash() {
        logger.info("Trash has been thrown away.");
        this.tilTrashFull = 5;
    }
    public boolean usable() {
        if (this.tilTrashFull != 0 && this.waterTank.hasWater()) {
            logger.info("This Coffee machine is usable!");
            return true;
        }
        logger.info("Can't use this Coffee machine!");
        return false;
    }

    public Optional<Drink> start(Drink drink) throws NotEnoughSupplies, MachineNeedsCare, DrinkDoesNotExist {
        if (!supplies.hasEnoughSupplies(drink)) {
            throw new NotEnoughSupplies("Not enough supplies for this drink!");
        }
        if (!usable()) {
            throw new MachineNeedsCare("Fill water tank or throw out trash!");
        }
        if (!knownDrinks.contains(drink.getType())) {
            throw new DrinkDoesNotExist("No such drink!");
        } else {
            supplies.takeSupplies(drink);
            waterTank.takeWater();
            tilTrashFull -= 1;
            madeDrinks.add(drink);
            Drink newDrink = new Drink(drink.getType(), drink.getNeededWater(),
                    drink.getNeededPowder(), drink.getNeededSugar(), drink.getNeededMilk());
            logger.info("New drink %s is ready.".formatted(newDrink.getType()));
            return Optional.of(newDrink);
        }
    }
}
