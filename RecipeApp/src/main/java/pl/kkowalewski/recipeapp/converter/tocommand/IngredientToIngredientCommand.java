package pl.kkowalewski.recipeapp.converter.tocommand;

import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import pl.kkowalewski.recipeapp.command.IngredientCommand;
import pl.kkowalewski.recipeapp.converter.Command;
import pl.kkowalewski.recipeapp.model.Ingredient;

@Component
public class IngredientToIngredientCommand implements Converter<Ingredient, IngredientCommand>,
        Command<Ingredient, IngredientCommand> {

    /*------------------------ FIELDS REGION ------------------------*/
    private final UnitOfMeasureToUnitOfMeasureCommand unitConverter;

    /*------------------------ METHODS REGION ------------------------*/

    public IngredientToIngredientCommand(UnitOfMeasureToUnitOfMeasureCommand unitConverter) {
        this.unitConverter = unitConverter;
    }

    @Synchronized
    @Nullable
    @Override
    public IngredientCommand convert(Ingredient ingredient) {
        if (ingredient == null) {
            return null;
        }

        return new IngredientCommand(ingredient.getId(), ingredient.getDescription(),
                ingredient.getAmount(), unitConverter.convert(ingredient.getUom()));
    }
}
