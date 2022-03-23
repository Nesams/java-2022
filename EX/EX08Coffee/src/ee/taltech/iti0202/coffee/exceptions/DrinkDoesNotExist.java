package ee.taltech.iti0202.coffee.exceptions;


public class DrinkDoesNotExist extends Exception {
    private final String exceptionMessage;

    public DrinkDoesNotExist(String exceptionMessage) {
        super(exceptionMessage);
        this.exceptionMessage = exceptionMessage;
    }
    public String getReason() {
        return this.exceptionMessage;
    }
}
