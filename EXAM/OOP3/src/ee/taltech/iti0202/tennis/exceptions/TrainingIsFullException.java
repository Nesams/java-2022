package ee.taltech.iti0202.tennis.exceptions;

public class TrainingIsFullException extends Exception {
    private final String exceptionMessage;

    public TrainingIsFullException(String exceptionMessage) {
        super(exceptionMessage);
        this.exceptionMessage = exceptionMessage;
    }
    public String getReason() {
        return this.exceptionMessage;
    }
}
