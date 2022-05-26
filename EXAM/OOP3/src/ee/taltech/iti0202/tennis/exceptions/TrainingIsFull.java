package ee.taltech.iti0202.tennis.exceptions;

public class TrainingIsFull extends Exception{
    private final String exceptionMessage;

    public TrainingIsFull(String exceptionMessage) {
        super(exceptionMessage);
        this.exceptionMessage = exceptionMessage;
    }
    public String getReason() {
        return this.exceptionMessage;
    }
}
