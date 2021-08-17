package exceptions;

public class ScriptException extends RuntimeException {
    public ScriptException() {
        super("Script error");
    }
}
