package mk.ukim.finki.wp2026.model.exceptions;

public class InvalidArgumentsException extends RuntimeException {
    public InvalidArgumentsException() {
        super("Please fill in all required fields.");
    }
}
