package exceptions;

public class UncorrectedPasswordException extends Exception {
    public UncorrectedPasswordException(String message) {
        super(message);
    }
}
