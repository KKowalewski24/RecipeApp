package pl.kkowalewski.recipeapp.converter.tocommand;

import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import pl.kkowalewski.recipeapp.command.RecipeCommand;
import pl.kkowalewski.recipeapp.converter.Command;
import pl.kkowalewski.recipeapp.model.Recipe;

@Component
public class RecipeToRecipeCommand implements Converter<Recipe, RecipeCommand>,
        Command<Recipe, RecipeCommand> {

    /*------------------------ FIELDS REGION ------------------------*/
    private final CategoryToCategoryCommand categoryConverter;
    private final IngredientToIngredientCommand ingredientConverter;
    private final NotesToNotesCommand notesConverter;

    /*------------------------ METHODS REGION ------------------------*/
    public RecipeToRecipeCommand(CategoryToCategoryCommand categoryConverter,
                                 IngredientToIngredientCommand ingredientConverter,
                                 NotesToNotesCommand notesConverter) {
        this.categoryConverter = categoryConverter;
        this.ingredientConverter = ingredientConverter;
        this.notesConverter = notesConverter;
    }

    @Synchronized
    @Nullable
    @Override
    public RecipeCommand convert(Recipe recipe) {
        if (recipe == null) {
            return null;
        }

        RecipeCommand recipeCommand = new RecipeCommand(recipe.getId(), recipe.getCookTime(),
                recipe.getPrepTime(), recipe.getDescription(), recipe.getDifficulty(),
                recipe.getDirections(), recipe.getServings(), recipe.getSource(),
                recipe.getUrl(), notesConverter.convert(recipe.getNotes()), recipe.getImage());

        if (recipe.getCategories() != null && recipe.getCategories().size() > 0) {
            recipe.getCategories().forEach(category -> recipeCommand
                    .getCategories().add(categoryConverter.convert(category)));
        }

        if (recipe.getIngredients() != null && recipe.getIngredients().size() > 0) {
            recipe.getIngredients().forEach(ingredient -> recipeCommand
                    .getIngredients().add(ingredientConverter.convert(ingredient)));
        }

        return recipeCommand;
    }
}
