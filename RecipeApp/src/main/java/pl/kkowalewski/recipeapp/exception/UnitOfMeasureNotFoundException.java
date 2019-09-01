package pl.kkowalewski.recipeapp.exception;

public class UnitOfMeasureNotFoundException extends RuntimeException {

    public UnitOfMeasureNotFoundException() {
    }

    public UnitOfMeasureNotFoundException(String message) {
        super(message);
    }

    public UnitOfMeasureNotFoundException(Throwable cause) {
        super(cause);
    }
}
