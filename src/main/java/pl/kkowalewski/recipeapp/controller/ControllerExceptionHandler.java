package pl.kkowalewski.recipeapp.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;
import pl.kkowalewski.recipeapp.exception.RecipeNotFoundException;

@ControllerAdvice
public class ControllerExceptionHandler {

    /*------------------------ FIELDS REGION ------------------------*/
    public static final String ERROR_400 = "400error";
    public static final String ERROR_404 = "404error";
    public static final String EXCEPTION = "exception";

    /*------------------------ METHODS REGION ------------------------*/
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(NumberFormatException.class)
    public ModelAndView handleNumberFormat(Exception exception) {
        return new ModelAndView(ERROR_400, EXCEPTION, exception);
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(RecipeNotFoundException.class)
    public ModelAndView handleNotFound(Exception exception) {
        return new ModelAndView(ERROR_404, EXCEPTION, exception);
    }
}
