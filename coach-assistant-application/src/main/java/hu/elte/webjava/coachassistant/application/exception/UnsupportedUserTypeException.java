package hu.elte.webjava.coachassistant.application.exception;

public class UnsupportedUserTypeException extends RuntimeException {

    public UnsupportedUserTypeException() {
        super("Unsupported user type.");
    }

    public UnsupportedUserTypeException(String message) {
        super(message);
    }
}
