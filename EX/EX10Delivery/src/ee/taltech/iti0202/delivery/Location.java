package ee.taltech.iti0202.delivery;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Optional;

public class Location {
    private String name;
    private ArrayList<Packet> packets;
    private HashMap<String, Integer> distances;
    private HashMap<String, Packet> packetss;

    public Location(String name) {
        this.name = name;
        this.packets = new ArrayList<>();
        this.packetss = new HashMap<>();
        this.distances = new HashMap<>();
    }
    public String getName() {
        return name;
    }

    public Integer getDistanceTo(String name) {
        if (distances.containsKey(name)) {
            return distances.get(name);
        }
        return Integer.MAX_VALUE;
    }

    void addPacket(Packet packet) {
        packetss.put(packet.getName(), packet);
    }

    public Optional<Packet> getPacket(String name) {
        if (packetss.containsKey(name)) {
            Packet packet = packetss.get(name);
            packetss.remove(name);
            return Optional.of(packet);
        }
        return Optional.empty();
    }

    public void addDistance(String location, int distance) {
        if(distances.containsKey(location)) {
            distances.put(location, distance);
        }
        distances.put(location, distance);
    }
}
