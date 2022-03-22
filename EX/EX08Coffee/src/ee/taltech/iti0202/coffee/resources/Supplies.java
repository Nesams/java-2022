package ee.taltech.iti0202.coffee.resources;

import ee.taltech.iti0202.coffee.drinks.Drink;

import java.util.HashMap;
import java.util.logging.Logger;


public class Supplies {
    private final HashMap<String, Integer> suppliesMap;
    private final static Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    public Supplies() {
        this.suppliesMap = new HashMap<>();
    }

    public void addSupplies(String product, int amount) {
        LOGGER.info("Adding supplies.");
        if (suppliesMap.containsKey(product)) {
            int oldAmount = suppliesMap.get(product);
            int newAmount = oldAmount + amount;
            suppliesMap.replace(product, newAmount);
        }
        suppliesMap.put(product, amount);
    }
    public void automateAddSupplies(Drink drink) {
        int oldSugar = suppliesMap.get("Sugar");
        int newSugar = oldSugar + drink.getNeededSugar();
        int oldMilk = suppliesMap.get("Milk");
        int newMilk = oldMilk + drink.getNeededMilk();
        int oldCoffeePowder = suppliesMap.get("Coffee Powder");
        int newCoffeePowder = oldCoffeePowder + drink.getNeededPowder();
        int oldCocoaPowder = suppliesMap.get("Cocoa Powder");
        int newCocoaPowder = oldCocoaPowder + drink.getNeededPowder();
        int oldTeaLeaves = suppliesMap.get("Tea");
        int newTeaLeaves= oldTeaLeaves + drink.getNeededPowder();
        if (drink.getType().equals(Drink.Type.COFFEE)) {
            LOGGER.info("Adding needed supplies for coffee.");
            suppliesMap.replace("Sugar", newSugar);
            suppliesMap.replace("Milk", newMilk);
            suppliesMap.replace("Coffee Powder", newCoffeePowder);
        }
        if (drink.getType().equals(Drink.Type.CAPPUCCINO)) {
            LOGGER.info("Adding needed supplies for cappuccino.");
            suppliesMap.replace("Sugar", newSugar);
            suppliesMap.replace("Milk", newMilk);
            suppliesMap.replace("Coffee Powder", newCoffeePowder);
        }
        if (drink.getType().equals(Drink.Type.COCOA)) {
            LOGGER.info("Adding needed supplies for cocoa.");
            suppliesMap.replace("Sugar", newSugar);
            suppliesMap.replace("Milk", newMilk);
            suppliesMap.replace("Cocoa Powder", newCocoaPowder);
        }
        if (drink.getType().equals(Drink.Type.TEA)) {
            LOGGER.info("Adding needed supplies for tea.");
            suppliesMap.replace("Sugar", newSugar);
            suppliesMap.replace("Tea", newTeaLeaves);
        }
    }

    public boolean hasEnoughSupplies(Drink drink) {
        if (drink.getType().equals(Drink.Type.COFFEE)) {
            if (suppliesMap.get("Sugar") >= drink.getNeededSugar()
                    && suppliesMap.get("Coffee Powder") >= drink.getNeededPowder()
                    && suppliesMap.get("Milk") >= drink.getNeededMilk()) {
                LOGGER.info("Enough supplies for coffee.");
                return true;
            }
            LOGGER.info("There is not enough supplies for coffee.");
            return false;
        }
        if (drink.getType().equals(Drink.Type.CAPPUCCINO)) {
            if (suppliesMap.get("Sugar") >= drink.getNeededSugar()
                    && suppliesMap.get("Coffee Powder") >= drink.getNeededPowder()
                    && suppliesMap.get("Milk") >= drink.getNeededMilk()) {
                LOGGER.info("Enough supplies for cappuccino.");
                return true;
            }
            LOGGER.info("There is not enough supplies for cappuccino.");
            return false;
        }
        if (drink.getType().equals(Drink.Type.COCOA)) {
            if (suppliesMap.get("Sugar") >= drink.getNeededSugar()
                    && suppliesMap.get("Cocoa Powder") >= drink.getNeededPowder()
                    && suppliesMap.get("Milk") >= drink.getNeededMilk()) {
                LOGGER.info("Enough supplies for cocoa.");
                return true;
            }
            LOGGER.info("There is not enough supplies for cocoa.");
            return false;
        }
        if (drink.getType().equals(Drink.Type.TEA)) {
            if (suppliesMap.get("Sugar") >= drink.getNeededSugar()
                    && suppliesMap.get("Tea") >= drink.getNeededPowder()) {
                LOGGER.info("Enough supplies for tea.");
                return true;
            }
            LOGGER.info("Not enough supplies for tea.");
            return false;
        }
        return false;
    }

    public void takeSupplies(Drink drink) {
        int oldSugar = suppliesMap.get("Sugar");
        int newSugar = oldSugar - drink.getNeededSugar();
        int oldMilk = suppliesMap.get("Milk");
        int newMilk = oldMilk - drink.getNeededMilk();
        int oldCoffeePowder = suppliesMap.get("Coffee Powder");
        int newCoffeePowder = oldCoffeePowder - drink.getNeededPowder();
        int oldCocoaPowder = suppliesMap.get("Cocoa Powder");
        int newCocoaPowder = oldCocoaPowder - drink.getNeededPowder();
        int oldTeaLeaves = suppliesMap.get("Tea");
        int newTeaLeaves = oldTeaLeaves - drink.getNeededPowder();
        if (drink.getType().equals(Drink.Type.COFFEE)) {
            suppliesMap.replace("Sugar", newSugar);
            suppliesMap.replace("Milk", newMilk);
            suppliesMap.replace("Coffee Powder", newCoffeePowder);
            LOGGER.info("Taking supplies for Coffee.");
        }
        if (drink.getType().equals(Drink.Type.CAPPUCCINO)) {
            suppliesMap.replace("Sugar", newSugar);
            suppliesMap.replace("Milk", newMilk);
            suppliesMap.replace("Coffee Powder", newCoffeePowder);
            LOGGER.info("Taking supplies for Cappuccino.");
        }
        if (drink.getType().equals(Drink.Type.COCOA)) {
            suppliesMap.replace("Sugar", newSugar);
            suppliesMap.replace("Milk", newMilk);
            suppliesMap.replace("Cocoa Powder", newCocoaPowder);
            LOGGER.info("Taking supplies for Cocoa.");
        }
        if (drink.getType().equals(Drink.Type.TEA)) {
            suppliesMap.replace("Tea", newTeaLeaves);
            suppliesMap.replace("Sugar", newSugar);
            LOGGER.info("Taking supplies for Tea.");
        }

    }

    public boolean hasEnoughCapsules(Drink drink) {
        if (drink.getType().equals(Drink.Type.COFFEE)) {
            if (suppliesMap.get("Coffee Capsule") >= 1) {
                LOGGER.info("Enough capsules for one coffee.");
                return true;
            }
            LOGGER.info("Not enough capsules for one coffee.");
            return false;
        }
        if (drink.getType().equals(Drink.Type.CAPPUCCINO)) {
            if (suppliesMap.get("Cappuccino Capsule") >= 1) {
                LOGGER.info("Enough capsules for one cappuccino.");
                return true;
            }
            LOGGER.info("Not enough capsules for one cappuccino.");
            return false;
        }
        if (drink.getType().equals(Drink.Type.COCOA)) {
            if (suppliesMap.get("Cocoa Capsule") >= 1) {
                LOGGER.info("Enough capsules for one cocoa.");
                return true;
            }
            LOGGER.info("Not enough capsules for one cocoa.");
            return false;
        }
        return false;
    }
    public void takeCapsule(Drink drink) {
        if (drink.getType().equals(Drink.Type.COFFEE)) {
            LOGGER.info("Taking coffee capsule.");
            suppliesMap.replace("Coffee Capsule", suppliesMap.get("Coffee Capsule"),
                    suppliesMap.get("Coffee Capsule") - 1);
        }
        if (drink.getType().equals(Drink.Type.CAPPUCCINO)) {
            LOGGER.info("Taking cappuccino capsule.");
            suppliesMap.replace("Cappuccino Capsule", suppliesMap.get("Cappuccino Capsule"),
                    suppliesMap.get("Cappuccino Capsule") - 1);
        }
        if (drink.getType().equals(Drink.Type.COCOA)) {
            LOGGER.info("Taking cocoa capsule.");
            suppliesMap.replace("Cocoa Capsule", suppliesMap.get("Cocoa Capsule"),
                    suppliesMap.get("Cocoa Capsule") - 1);
        }
    }
}
