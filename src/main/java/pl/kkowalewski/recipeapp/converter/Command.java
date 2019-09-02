package pl.kkowalewski.recipeapp.converter;

public interface Command<T, U> {
    U convert(T source);
}
