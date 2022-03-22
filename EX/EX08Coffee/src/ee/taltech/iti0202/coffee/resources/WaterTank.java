package ee.taltech.iti0202.coffee.resources;

import java.util.logging.Logger;

public class WaterTank {
    public int amountOfWater;
    final int six = 6;
    Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    public WaterTank(int amountOfWater) {
        this.amountOfWater = amountOfWater;
    }

    public boolean hasWater() {
        logger.info("Checking if Water tank has enough water.");
        return amountOfWater != 0;
    }

    public void fill() {
        logger.info("Filling the WaterTank.");
        amountOfWater = six;
    }

    public void takeWater() {
        logger.info("Taking water from water tank.");
        amountOfWater -= 1;
    }
}
