package exceptions;

public class NotAuthorizationException extends Exception {
    public NotAuthorizationException() {
        super("You are not authorize");
    }
}
