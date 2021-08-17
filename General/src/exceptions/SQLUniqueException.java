package exceptions;

public class SQLUniqueException extends Exception {
    public SQLUniqueException() {
        super("This element already exist in DB, should be unique");
    }
}
