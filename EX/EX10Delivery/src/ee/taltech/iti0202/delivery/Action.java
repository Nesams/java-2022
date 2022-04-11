package ee.taltech.iti0202.delivery;

import java.util.ArrayList;
import java.util.List;

public class Action {
    private Location location;
    public List<String> take;
    public List<String> deposit;

    public Action(Location location) {

        this.location = location;
        this.take = new ArrayList<>();
        this.deposit = new ArrayList<>();
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
        deposit.add(packetName);
    }

    void addTake(String packetName) {
        take.add(packetName);
    }
}
