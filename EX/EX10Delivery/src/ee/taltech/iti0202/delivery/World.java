package ee.taltech.iti0202.delivery;

import java.util.*;

public class World {
    private Map<String, Location> locations;
    private Map<String, Courier> couriers;

    public World() {
        this.locations = new HashMap<>();
        this.couriers = new HashMap<>();
    }

    public Optional<Location> addLocation(String name, List<String> otherLocations, List<Integer> distances) {
        Location location = new Location(name);
        locations.put(name, location);
        return Optional.of(location);
    }

    public void tick() {
        for(Courier courier: couriers.values()) {
            if(courier.getLocation().isPresent()) {
                Action nextAction = courier.getStrategy().getAction();
                Location currentlocation = courier.getLocation().get();
                for(String pack: nextAction.getDeposit()) {
                    courier.removePacket(courier.getPacketByName(pack));
                }
                for(String pack: nextAction.getTake()) {
                    Packet packet = currentlocation.getPacket(pack).get();
                    courier.addPacket(packet);
                }
                Location nextLocation = nextAction.getLocation();
                int distanceToNextLocation = nextLocation.getDistanceTo(nextLocation.getName());
                courier.setDistanceToNextLocation(distanceToNextLocation);
                courier.setLocation(nextLocation);
                courier.move();
            }
            courier.move();
        }

    }

    public void giveStrategy(String courierName, Strategy strategy) {
        couriers.get(courierName).setStrategy(strategy);
    }

    public Optional<Courier> addCourier(String name, String location) {
        Courier courier = new Courier(name);
        courier.setLocation(locations.get(location));
        couriers.put(name, courier);
        return Optional.of(courier);
    }
}
