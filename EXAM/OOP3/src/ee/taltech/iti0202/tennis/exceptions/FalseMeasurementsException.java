package ee.taltech.iti0202.tennis.exceptions;

public class FalseMeasurementsException extends Exception {
    private final String exceptionMessage;

    public FalseMeasurementsException(String exceptionMessage) {
        super(exceptionMessage);
        this.exceptionMessage = exceptionMessage;
    }
    public String getReason() {
        return this.exceptionMessage;
    }
}
