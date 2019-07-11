package pl.kkowalewski.recipeapp.exception;

public class UnitsException extends RuntimeException {

    public UnitsException() {
    }

    public UnitsException(String message) {
        super(message);
    }

    public UnitsException(Throwable cause) {
        super(cause);
    }
}
