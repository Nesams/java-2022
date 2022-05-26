package ee.taltech.iti0202.tennis.exceptions;

public class TrainerAlreadyHasTrainingException extends Exception {
    private final String exceptionMessage;

    public TrainerAlreadyHasTrainingException(String exceptionMessage) {
        super(exceptionMessage);
        this.exceptionMessage = exceptionMessage;
    }
    public String getReason() {
        return this.exceptionMessage;
    }
}
