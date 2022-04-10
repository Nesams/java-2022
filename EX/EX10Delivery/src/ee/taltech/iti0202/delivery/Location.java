package ee.taltech.iti0202.delivery;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Optional;

public class Location {
    private String name;
    private ArrayList<Packet> packets;
    private HashMap<String, Integer> distances;

    public Location(String name) {
        this.name = name;
        this.packets = new ArrayList<>();
        this.distances = new HashMap<>();
    }

    public String getName() {
        return name;
    }

    Integer getDistanceTo(String name) {
        if (distances.containsKey(name)) {
            return distances.get(name);
        }
        return Integer.MAX_VALUE;
    }

    void addPacket(Packet packet) {
        packets.add(packet);
    }

    Optional<Packet> getPacket(String name) {
        for (Packet packet: packets) {
            if (packet.getName().equals(name)) {
                packets.remove(packet);
                return Optional.of(packet);
            }
        }
        return Optional.empty();
    }

    public void addDistance(String location, int distance) {
        distances.putIfAbsent(location, distance);
    }
}
