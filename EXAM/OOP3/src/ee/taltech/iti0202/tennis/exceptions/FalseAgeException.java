package ee.taltech.iti0202.tennis.exceptions;

public class FalseAgeException extends Exception{
    private final String exceptionMessage;

    public FalseAgeException(String exceptionMessage) {
        super(exceptionMessage);
        this.exceptionMessage = exceptionMessage;
    }
    public String getReason() {
        return this.exceptionMessage;
    }
}
