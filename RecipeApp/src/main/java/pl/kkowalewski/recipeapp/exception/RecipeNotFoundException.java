package pl.kkowalewski.recipeapp.exception;

public class RecipeNotFoundException extends RuntimeException {

    public RecipeNotFoundException() {
    }

    public RecipeNotFoundException(String message) {
        super(message);
    }

    public RecipeNotFoundException(Throwable cause) {
        super(cause);
    }
}
