package ee.taltech.iti0202.files.input;

public class FileReaderException extends RuntimeException {
    private final String message;

    /**
     * @param message
     * @param cause
     */
    public FileReaderException(String message, Throwable cause) {
        super(message, cause);
        this.message = "No such file";
    }

    @Override
    public String toString() {
        return this.message;
    }
}
