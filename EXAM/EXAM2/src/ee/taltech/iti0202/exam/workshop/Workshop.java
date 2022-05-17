package ee.taltech.iti0202.exam.workshop;

import javax.swing.text.html.parser.Entity;
import java.util.*;
import java.util.stream.Collectors;

public class Workshop {

    private final String name;
    private final ArrayList<Mechanic> mechanics;
    private final ArrayList<Car> database;


    public Workshop(String name) {
        this.name = name;
        this.mechanics = new ArrayList<>();
        this.database = new ArrayList<>();
    }

    public boolean addMechanic(Mechanic mechanic) {
        if (mechanic.getWorkshop() == null) {
            mechanics.add(mechanic);
            mechanic.setWorkshop(this);
            return true;
        }
        return false;
    }

    public ArrayList<Car> getDatabase() {
        return database;
    }
    public void addFixedCarToDatabase(Car car) {
        database.add(car);
    }

    public boolean registerCarForRepair(Car car, Mechanic mechanic) {
        if (mechanics.contains(mechanic) && !mechanic.getCarsToBeFixed().contains(car)) {
            car.setFixed(false);
            mechanic.addCarToBeFixed(car);
            return true;
        }
        return false;
    }

    public Optional<Car> getCarWithTheMostFixedTimes() {
        if (!database.isEmpty()) {
            return Optional.ofNullable(database.stream().sorted(Comparator.comparing(Car::getTimesFixed).reversed()).collect(Collectors.toList()).get(0));
        }
        return Optional.empty();
    }

    public List<Mechanic> getAllMechanics() {
        return mechanics;
    }

    public String getName() {
        return name;
    }
}
