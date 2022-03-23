package ee.taltech.iti0202.coffee.exceptions;

public class NotEnoughSupplies extends Exception {
    private final String exceptionMessage;

    public NotEnoughSupplies(String exceptionMessage) {
        super(exceptionMessage);
        this.exceptionMessage = exceptionMessage;
    }
    public String getReason() {
        return this.exceptionMessage;
    }
}
