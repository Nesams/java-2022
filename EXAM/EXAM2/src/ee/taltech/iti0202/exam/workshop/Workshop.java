package ee.taltech.iti0202.exam.workshop;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
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
            return Optional.ofNullable(database.stream()
                    .sorted(Comparator.comparing(Car::getTimesFixed).reversed()).collect(Collectors.toList()).get(0));
        }
        return Optional.empty();
    }

    public List<Mechanic> getAllMechanics() {
        return mechanics;
    }

    public String getName() {
        return name;
    }

    public static void main(String[] args) {

        Workshop workshop = new Workshop("TalTech");
        Workshop workshop1 = new Workshop("Tartu shop");
        Mechanic mechanic1 = new Mechanic("Rein");
        Mechanic mechanic2 = new Mechanic("Ljulja");
        Mechanic mechanic3 = new Mechanic("Guido");
        Car car1 = new Car("TALTECH1");
        Car car2 = new Car("YOLOLO");

// cannot do stuff yet
        System.out.println(mechanic1.fixCar(car1));  // false
        System.out.println(workshop.registerCarForRepair(car1, mechanic1));  // false

        workshop.addMechanic(mechanic1);
        workshop.addMechanic(mechanic2);
        System.out.println(workshop.registerCarForRepair(car1, mechanic1));  // true
        System.out.println(car1.isFixed());  // false
        System.out.println(mechanic2.fixCar(car1));  // false - not in her list
        System.out.println(mechanic1.fixCar(car1));  // true
        System.out.println(car1.isFixed());  // true
        System.out.println(mechanic1.fixCar(car2));  // true
        System.out.println(car2.isFixed());  // true
        System.out.println(mechanic1.fixCar(car1));  // true
        System.out.println(car1.isFixed());  // true

        System.out.println(workshop1.addMechanic(mechanic3));  // true
        System.out.println(workshop1.addMechanic(mechanic3));  // false - cannot anymore

        System.out.println(mechanic3.fixCar(car2));  // false
        System.out.println(workshop1.registerCarForRepair(car2, mechanic3));  // true
        System.out.println(workshop1.registerCarForRepair(car2, mechanic3));  // false - cannot anymore
        System.out.println(workshop1.registerCarForRepair(car2, mechanic2));  // false
        System.out.println(mechanic3.fixCar(car2));  // true
        System.out.println(workshop1.registerCarForRepair(car2, mechanic3));  // true
        System.out.println(mechanic3.fixCar(car2));  // true
        System.out.println(workshop1.registerCarForRepair(car1, mechanic3));  // true
        System.out.println(mechanic3.fixCar(car1));  // true
        System.out.println(mechanic3.fixCar(car1));  // false


        System.out.println(workshop.getCarWithTheMostFixedTimes().get() == car1);  // true
        System.out.println(workshop1.getCarWithTheMostFixedTimes().get() == car2);  // true
    }
}
