package ee.taltech.iti0202.delivery;

import java.util.*;
import java.util.stream.Collectors;

public class World {
    private Map<String, Location> locations;
    private Map<String, Courier> couriers;

    public World() {
        this.locations = new HashMap<>();
        this.couriers = new HashMap<>();
    }

    public Optional<Location> addLocation(String name, List<String> otherLocations, List<Integer> distances) {
        if (checkIfOtherLocationsContainsAllLocations(otherLocations) && !locations.containsKey(name)) {
            Location location = new Location(name);
            locations.put(name, location);
            for (int i = 0; i < otherLocations.size(); i++) {
                location.addDistance(otherLocations.get(i), distances.get(i));
            }
            updateLocations(location, otherLocations, distances);
            return Optional.of(location);
        }
        return Optional.empty();
    }

    public boolean checkIfOtherLocationsContainsAllLocations(List<String> otherLocations) {
        List<String> locationsNames = new ArrayList<>(locations.keySet());
        Set<String> otherLocationsSet = new HashSet<>(otherLocations);
        return otherLocationsSet.containsAll(locationsNames);
    }

    public void updateLocations(Location location, List<String> otherLocations, List<Integer> distances) {
        for (int i = 0; i < otherLocations.size(); i++) {
            String locationName = otherLocations.get(i);
            locations.get(locationName).addDistance(location.getName(), distances.get(i));
        }
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
                Location nextLocation = nextAction.getGoTo();
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
        if (!couriers.containsKey(name) && locations.containsKey(location)) {
            Courier courier = new Courier(name);
            courier.setLocation(locations.get(location));
            couriers.put(name, courier);
            return Optional.of(courier);
        }
        return Optional.empty();
    }
}
