package ee.taltech.iti0202.coffee.coffeeMachine;

import ee.taltech.iti0202.coffee.drinks.Drink;
import ee.taltech.iti0202.coffee.exceptions.DrinkDoesNotExist;
import ee.taltech.iti0202.coffee.exceptions.MachineNeedsCare;
import ee.taltech.iti0202.coffee.resources.Supplies;
import ee.taltech.iti0202.coffee.resources.WaterTank;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

public class AutomaticMachine implements CoffeeMachineInterface {
    private final String name;
    private final WaterTank waterTank;
    private final Supplies supplies;
    private final List<Drink.Type> knownDrinks;
    private int tilTrashFull;
    private ArrayList<Drink> madeDrinks;
    private final static Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    public AutomaticMachine(String name, WaterTank waterTank, Supplies supplies, List<Drink.Type> knownDrinks) {
        this.name = name;
        this.waterTank = waterTank;
        this.supplies = supplies;
        this.knownDrinks = knownDrinks;
        this.tilTrashFull = 5;
        this.madeDrinks = new ArrayList<>();
    }

    @Override
    public boolean usable() {
        if(this.tilTrashFull != 0 && this.waterTank.hasWater()) {
            LOGGER.info("This Coffee machine is usable!");
            return true;
        }
        LOGGER.info("Can't use this Coffee machine!");
        return false;
    }

    @Override
    public Optional<Drink> start(Drink drink) throws MachineNeedsCare, DrinkDoesNotExist{
        if(!supplies.hasEnoughSupplies(drink)) {
            LOGGER.info("Stacking up supplies.");
            supplies.automateAddSupplies(drink);
            supplies.takeSupplies(drink);
            waterTank.takeWater();
            tilTrashFull -= 1;
            Drink newDrink = new Drink(drink.getType(), drink.getNeededWater(), drink.getNeededPowder(),
                    drink.getNeededSugar(), drink.getNeededMilk());
            madeDrinks.add(newDrink);
            LOGGER.info("New %s drink is ready.".formatted(drink.getType()));
            return Optional.of(newDrink);
        }
        if (!usable()) {
            throw new MachineNeedsCare("Fill water tank or throw out trash!");
        }
        if(!knownDrinks.contains(drink.getType())) {
            throw new DrinkDoesNotExist("No such drink!");
        } else {
            supplies.takeSupplies(drink);
            waterTank.takeWater();
            tilTrashFull -= 1;
            Drink newDrink = new Drink(drink.getType(), drink.getNeededWater(), drink.getNeededPowder(),
                    drink.getNeededSugar(), drink.getNeededMilk());
            madeDrinks.add(newDrink);
            LOGGER.info("New %s drink is ready.".formatted(newDrink.getType()));
            return Optional.of(newDrink);
        }
    }
}
