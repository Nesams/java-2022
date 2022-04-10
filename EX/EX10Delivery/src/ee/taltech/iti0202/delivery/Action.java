package ee.taltech.iti0202.delivery;

import java.util.ArrayList;
import java.util.List;

public class Action {
    private Location location;
    public List<String> take = new ArrayList<>();
    public List<String> deposit = new ArrayList<>();

    public Action(Location location) {
        this.location = location;
    }

    List<String> getDeposit() {
        return deposit;
    }

    List<String> getTake() {
        return take;
    }

    Location getGoTo() {
        return location;
    }

    void addDeposit(String packetName) {
        take.add(packetName);
    }

    void addTake(String packetName) {
        deposit.add(packetName);
    }
}
