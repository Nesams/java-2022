package ee.taltech.iti0202.coffee.exceptions;

public class NotEnoughSupplies extends Exception {
    public NotEnoughSupplies(String exceptionMessage) {
        super(exceptionMessage);
    }
    public NotEnoughSupplies(String exceptionMessage, Throwable cause) {
        super(exceptionMessage, cause);
    }
}
