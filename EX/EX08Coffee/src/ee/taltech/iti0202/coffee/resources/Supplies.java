package ee.taltech.iti0202.coffee.resources;

import ee.taltech.iti0202.coffee.drinks.Drink;

import java.util.HashMap;
import java.util.logging.Logger;


public class Supplies {
    private final HashMap<String, Integer> suppliesMap;
    Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    public Supplies() {
        this.suppliesMap = new HashMap<>();
    }

    public HashMap<String, Integer> getSuppliesMap() {
        return suppliesMap;
    }

    public void addSupplies(String product, int amount) {
        logger.info("Adding supplies.");
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
        int oldCoffeePowder = suppliesMap.get("Coffee Beans");
        int newCoffeePowder = oldCoffeePowder + drink.getNeededPowder();
        int oldCocoaPowder = suppliesMap.get("Cocoa Powder");
        int newCocoaPowder = oldCocoaPowder + drink.getNeededPowder();
        int oldTeaLeaves = suppliesMap.get("Tea");
        int newTeaLeaves = oldTeaLeaves + drink.getNeededPowder();
        if (drink.getType().equals(Drink.Type.COFFEE)) {
            logger.info("Adding needed supplies for coffee.");
            suppliesMap.replace("Sugar", newSugar);
            suppliesMap.replace("Milk", newMilk);
            suppliesMap.replace("Coffee Beans", newCoffeePowder);
        }
        if (drink.getType().equals(Drink.Type.CAPPUCCINO)) {
            logger.info("Adding needed supplies for cappuccino.");
            suppliesMap.replace("Sugar", newSugar);
            suppliesMap.replace("Milk", newMilk);
            suppliesMap.replace("Coffee Beans", newCoffeePowder);
        }
        if (drink.getType().equals(Drink.Type.COCOA)) {
            logger.info("Adding needed supplies for cocoa.");
            suppliesMap.replace("Sugar", newSugar);
            suppliesMap.replace("Milk", newMilk);
            suppliesMap.replace("Cocoa Powder", newCocoaPowder);
        }
        if (drink.getType().equals(Drink.Type.TEA)) {
            logger.info("Adding needed supplies for tea.");
            suppliesMap.replace("Sugar", newSugar);
            suppliesMap.replace("Tea", newTeaLeaves);
        }
    }

    public boolean hasEnoughSupplies(Drink drink) {
        if (drink.getType().equals(Drink.Type.COFFEE)) {
            if (suppliesMap.get("Sugar") >= drink.getNeededSugar()
                    && suppliesMap.get("Coffee Beans") >= drink.getNeededPowder()
                    && suppliesMap.get("Milk") >= drink.getNeededMilk()) {
                logger.info("Enough supplies for coffee.");
                return true;
            }
            logger.info("There is not enough supplies for coffee.");
            return false;
        }
        if (drink.getType().equals(Drink.Type.CAPPUCCINO)) {
            if (suppliesMap.get("Sugar") >= drink.getNeededSugar()
                    && suppliesMap.get("Coffee Beans") >= drink.getNeededPowder()
                    && suppliesMap.get("Milk") >= drink.getNeededMilk()) {
                logger.info("Enough supplies for cappuccino.");
                return true;
            }
            logger.info("There is not enough supplies for cappuccino.");
            return false;
        }
        if (drink.getType().equals(Drink.Type.COCOA)) {
            if (suppliesMap.get("Sugar") >= drink.getNeededSugar()
                    && suppliesMap.get("Cocoa Powder") >= drink.getNeededPowder()
                    && suppliesMap.get("Milk") >= drink.getNeededMilk()) {
                logger.info("Enough supplies for cocoa.");
                return true;
            }
            logger.info("There is not enough supplies for cocoa.");
            return false;
        }
        if (drink.getType().equals(Drink.Type.TEA)) {
            if (suppliesMap.get("Sugar") >= drink.getNeededSugar()
                    && suppliesMap.get("Tea") >= drink.getNeededPowder()) {
                logger.info("Enough supplies for tea.");
                return true;
            }
            logger.info("Not enough supplies for tea.");
            return false;
        }
        return false;
    }

    public void takeSupplies(Drink drink) {
        int oldSugar = suppliesMap.get("Sugar");
        int newSugar = oldSugar - drink.getNeededSugar();
        int oldMilk = suppliesMap.get("Milk");
        int newMilk = oldMilk - drink.getNeededMilk();
        int oldCoffeePowder = suppliesMap.get("Coffee Beans");
        int newCoffeePowder = oldCoffeePowder - drink.getNeededPowder();
        int oldCocoaPowder = suppliesMap.get("Cocoa Powder");
        int newCocoaPowder = oldCocoaPowder - drink.getNeededPowder();
        int oldTeaLeaves = suppliesMap.get("Tea");
        int newTeaLeaves = oldTeaLeaves - drink.getNeededPowder();
        if (drink.getType().equals(Drink.Type.COFFEE)) {
            suppliesMap.replace("Sugar", newSugar);
            suppliesMap.replace("Milk", newMilk);
            suppliesMap.replace("Coffee Beans", newCoffeePowder);
            logger.info("Taking supplies for Coffee.");
        }
        if (drink.getType().equals(Drink.Type.CAPPUCCINO)) {
            suppliesMap.replace("Sugar", newSugar);
            suppliesMap.replace("Milk", newMilk);
            suppliesMap.replace("Coffee Beans", newCoffeePowder);
            logger.info("Taking supplies for Cappuccino.");
        }
        if (drink.getType().equals(Drink.Type.COCOA)) {
            suppliesMap.replace("Sugar", newSugar);
            suppliesMap.replace("Milk", newMilk);
            suppliesMap.replace("Cocoa Powder", newCocoaPowder);
            logger.info("Taking supplies for Cocoa.");
        }
        if (drink.getType().equals(Drink.Type.TEA)) {
            suppliesMap.replace("Tea", newTeaLeaves);
            suppliesMap.replace("Sugar", newSugar);
            logger.info("Taking supplies for Tea.");
        }
    }

    public boolean hasEnoughCapsules(Drink drink) {
        if (drink.getType().equals(Drink.Type.COFFEE)) {
            if (suppliesMap.get("Coffee Capsule") >= 1) {
                logger.info("Enough capsules for one coffee.");
                return true;
            }
            logger.info("Not enough capsules for one coffee.");
            return false;
        }
        if (drink.getType().equals(Drink.Type.CAPPUCCINO)) {
            if (suppliesMap.get("Cappuccino Capsule") >= 1) {
                logger.info("Enough capsules for one cappuccino.");
                return true;
            }
            logger.info("Not enough capsules for one cappuccino.");
            return false;
        }
        if (drink.getType().equals(Drink.Type.COCOA)) {
            if (suppliesMap.get("Cocoa Capsule") >= 1) {
                logger.info("Enough capsules for one cocoa.");
                return true;
            }
            logger.info("Not enough capsules for one cocoa.");
            return false;
        }
        return false;
    }
    public void takeCapsule(Drink drink) {
        if (drink.getType().equals(Drink.Type.COFFEE)) {
            logger.info("Taking coffee capsule.");
            suppliesMap.replace("Coffee Capsule", suppliesMap.get("Coffee Capsule"),
                    suppliesMap.get("Coffee Capsule") - 1);
        }
        if (drink.getType().equals(Drink.Type.CAPPUCCINO)) {
            logger.info("Taking cappuccino capsule.");
            suppliesMap.replace("Cappuccino Capsule", suppliesMap.get("Cappuccino Capsule"),
                    suppliesMap.get("Cappuccino Capsule") - 1);
        }
        if (drink.getType().equals(Drink.Type.COCOA)) {
            logger.info("Taking cocoa capsule.");
            suppliesMap.replace("Cocoa Capsule", suppliesMap.get("Cocoa Capsule"),
                    suppliesMap.get("Cocoa Capsule") - 1);
        }
    }
}
