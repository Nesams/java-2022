package ee.taltech.iti0202.coffee.coffeeMachine;

import ee.taltech.iti0202.coffee.capsules.Capsule;
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

public class CapsuleMachine implements CoffeeMachineInterface {
    private final List<Drink.Type> knownCapsules;
    private final String name;
    private final WaterTank waterTank;
    private final Supplies supplies;
    private int tilTrashFull;
    private Capsule capsule;
    private ArrayList<Drink> madeDrinks;
    private final static Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    public CapsuleMachine(String name, WaterTank waterTank, Supplies supplies, List<Drink.Type> knownCapsules) {
        this.name = name;
        this.waterTank = waterTank;
        this.supplies= supplies;
        this.knownCapsules = knownCapsules;
        this.capsule = null;
        this.tilTrashFull = 10;
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
    public Optional<Drink> start(Drink drink) throws NotEnoughSupplies, MachineNeedsCare, DrinkDoesNotExist {
        if (!supplies.hasEnoughCapsules(drink)) {
            throw new NotEnoughSupplies("Not enough supplies for this drink!");
        }
        if (!usable()) {
            throw new MachineNeedsCare("Fill water tank or throw out trash!");
        }
        if (!knownCapsules.contains(drink.getType())) {
            throw new DrinkDoesNotExist("No such capsule!");
        } else {
            supplies.takeCapsule(drink);
            waterTank.takeWater();
            tilTrashFull -= 1;
            Drink newDrink = new Drink(drink.getType(), drink.getNeededWater(), drink.getNeededPowder(),
                    drink.getNeededSugar(), drink.getNeededMilk());
            madeDrinks.add(newDrink);
            LOGGER.info("New drink %s is ready.".formatted(newDrink.getType()));
            return Optional.of(newDrink);
        }
    }
}
