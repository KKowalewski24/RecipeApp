package pl.kkowalewski.recipeapp.exception;

public class CategoryException extends RuntimeException {

    public CategoryException() {
    }

    public CategoryException(String message) {
        super(message);
    }

    public CategoryException(Throwable cause) {
        super(cause);
    }
}
