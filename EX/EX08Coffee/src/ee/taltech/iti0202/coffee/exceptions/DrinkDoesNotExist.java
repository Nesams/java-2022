package ee.taltech.iti0202.coffee.exceptions;

public class DrinkDoesNotExist extends Exception {
    public DrinkDoesNotExist(String exceptionMessage) {
        super(exceptionMessage);
    }
}
