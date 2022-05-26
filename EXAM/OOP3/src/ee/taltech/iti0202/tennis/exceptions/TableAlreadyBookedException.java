package ee.taltech.iti0202.tennis.exceptions;

public class TableAlreadyBookedException extends Exception {
    private final String exceptionMessage;

    public TableAlreadyBookedException(String exceptionMessage) {
        super(exceptionMessage);
        this.exceptionMessage = exceptionMessage;
    }
    public String getReason() {
        return this.exceptionMessage;
    }
}
