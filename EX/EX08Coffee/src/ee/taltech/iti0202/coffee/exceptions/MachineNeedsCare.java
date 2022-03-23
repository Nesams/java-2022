package ee.taltech.iti0202.coffee.exceptions;

public class MachineNeedsCare extends Exception {
    private final String message;

    public MachineNeedsCare(String message) {
        super(message);
        this.message = message;
    }

    public String getReason() {
        return this.message;
    }
}
