package ee.taltech.iti0202.delivery;

public class Packet {
    private final String name;
    private final Location target;

    public Packet(String name, Location target) {
        this.name = name;
        this.target = target;
    }
    String getName() {
        return name;
    }
    Location getTarget() {
        return target;
    }
}
