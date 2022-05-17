package ee.taltech.iti0202.exam.workshop;

import java.util.List;
import java.util.Optional;

public class Workshop {

    public Workshop(String name) {
    }

    public boolean addMechanic(Mechanic mechanic) {
        return true;
    }

    public boolean registerCarForRepair(Car car, Mechanic mechanic) {
        return true;
    }

    public Optional<Car> getCarWithTheMostFixedTimes() {
        return Optional.empty();
    }

    public List<Mechanic> getAllMechanics() {
        return null;
    }

    public String getName() {
        return null;
    }
}
