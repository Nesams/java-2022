package ee.taltech.iti0202.exam.workshop;
import java.util.ArrayList;
import java.util.List;

public class Mechanic {

    private final String name;
    private final ArrayList<Car> fixedCars;
    private final ArrayList<Car> needsToBeFixed;
    private Workshop workshop;


    public Mechanic(String name) {
        this.name = name;
        this.fixedCars = new ArrayList<>();
        this.needsToBeFixed = new ArrayList<>();
        this.workshop = null;
    }

    public boolean fixCar(Car car) {
        if (!car.isFixed() && needsToBeFixed.contains(car)) {
            car.setFixed(true);
            fixedCars.add(car);
            workshop.addFixedCarToDatabase(car);
            needsToBeFixed.remove(car);
            car.setTimesFixed();
            return true;
        }
        return false;
    }
    public void addCarToBeFixed(Car car) {
        needsToBeFixed.add(car);
    }

    public List<Car> getCarsToBeFixed() {
        return this.needsToBeFixed;
    }

    public void setWorkshop(Workshop workshop) {
        this.workshop = workshop;
    }

    public Workshop getWorkshop() {
        return this.workshop;
    }
    public String getName() {
        return this.name;
    }
}
