package ee.taltech.iti0202.coffee.resources;

import java.util.logging.Logger;

public class WaterTank {
    public int amountOfWater;
    Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    public WaterTank(int amountOfWater) {
        this.amountOfWater = amountOfWater;
    }

    public boolean hasWater() {
        LOGGER.info("Checking if Water tank has enough water.");
        return amountOfWater != 0;
    }

    public void fill() {
        LOGGER.info("Filling the WaterTank.");
        amountOfWater = 6;
    }

    public void takeWater() {
        LOGGER.info("Taking water from water tank.");
        amountOfWater -= 1;
    }
}
