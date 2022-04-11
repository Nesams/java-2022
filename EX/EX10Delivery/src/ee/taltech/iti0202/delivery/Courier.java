package ee.taltech.iti0202.delivery;

import java.util.ArrayList;
import java.util.Optional;

public class Courier {
    private int distanceToNextLocation;
    private String name;
    private ArrayList<Packet> packages;
    private Strategy strategy;
    private Location location;

    public Courier(String name) {
        this.name = name;
        this.location = null;
        this.distanceToNextLocation = 0;
        this.packages = new ArrayList<>();
        this.strategy = null;
    }

    public String getName() {
        return name;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public void addPacket(Packet packet) {
        packages.add(packet);
    }

    public int getDistanceToNextLocation() {
        return distanceToNextLocation;
    }

    public void move() {
        distanceToNextLocation--;
    }

    public void setDistanceToNextLocation(int distance) {
        distanceToNextLocation = distance;
    }

    public Optional<Location> getLocation() {
        if (distanceToNextLocation == 0) {
            return Optional.of(this.location);
        }
        return Optional.empty();
    }

    void setStrategy(Strategy strategy) {
        this.strategy = strategy;
    }

    Strategy getStrategy() {
        return this.strategy;
    }

    public ArrayList<Packet> getPackages() {
        return packages;
    }
    public void removePacket(Packet packet) {
        if (packages.contains(packet)) {
            packages.remove(packet);
        }
    }
    public Packet getPacketByName(String packetName) {
        for (Packet packet: packages) {
            if (packet.getName().equals(packetName)) {
                packages.remove(packet);
                return packet;
            }
        }
        return null;
    }
}
