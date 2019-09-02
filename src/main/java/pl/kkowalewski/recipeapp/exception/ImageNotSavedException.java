package pl.kkowalewski.recipeapp.exception;

import java.io.IOException;

public class ImageNotSavedException extends IOException {

    public ImageNotSavedException() {
    }

    public ImageNotSavedException(String message) {
        super(message);
    }

    public ImageNotSavedException(Throwable cause) {
        super(cause);
    }
}
