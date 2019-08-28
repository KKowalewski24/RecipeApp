package pl.kkowalewski.recipeapp.converter.commandto;

import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import pl.kkowalewski.recipeapp.command.RecipeCommand;
import pl.kkowalewski.recipeapp.converter.Command;
import pl.kkowalewski.recipeapp.model.Recipe;

@Component
public class RecipeCommandToRecipe implements Converter<RecipeCommand, Recipe>,
        Command<RecipeCommand, Recipe> {

    /*------------------------ FIELDS REGION ------------------------*/
    private final CategoryCommandToCategory categoryConverter;
    private final IngredientCommandToIngredient ingredientConverter;
    private final NotesCommandToNotes notesConverter;

    /*------------------------ METHODS REGION ------------------------*/
    public RecipeCommandToRecipe(CategoryCommandToCategory categoryConverter,
                                 IngredientCommandToIngredient ingredientConverter,
                                 NotesCommandToNotes notesConverter) {
        this.categoryConverter = categoryConverter;
        this.ingredientConverter = ingredientConverter;
        this.notesConverter = notesConverter;
    }

    @Synchronized
    @Nullable
    @Override
    public Recipe convert(RecipeCommand recipeCommand) {
        if (recipeCommand == null) {
            return null;
        }

        Recipe recipe = new Recipe(recipeCommand.getId(), recipeCommand.getCookTime(),
                recipeCommand.getPrepTime(), recipeCommand.getDescription(),
                recipeCommand.getDifficulty(), recipeCommand.getDirections(),
                recipeCommand.getServings(), recipeCommand.getSource(),
                recipeCommand.getUrl(), notesConverter.convert(recipeCommand.getNotes()));

        if (recipeCommand.getCategories() != null && recipeCommand.getCategories().size() > 0) {
            recipeCommand.getCategories().forEach(category -> recipe.getCategories()
                    .add(categoryConverter.convert(category)));
        }

        if (recipeCommand.getIngredients() != null && recipeCommand.getIngredients().size() > 0) {
            recipeCommand.getIngredients().forEach(ingredient -> recipe.getIngredients()
                    .add(ingredientConverter.convert(ingredient)));
        }

        return recipe;
    }
}
