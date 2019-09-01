package pl.kkowalewski.recipeapp.service.ingredient;

import org.springframework.stereotype.Service;
import pl.kkowalewski.recipeapp.command.IngredientCommand;
import pl.kkowalewski.recipeapp.converter.commandto.IngredientCommandToIngredient;
import pl.kkowalewski.recipeapp.converter.tocommand.IngredientToIngredientCommand;
import pl.kkowalewski.recipeapp.exception.UnitOfMeasureNotFoundException;
import pl.kkowalewski.recipeapp.model.Ingredient;
import pl.kkowalewski.recipeapp.model.Recipe;
import pl.kkowalewski.recipeapp.repository.RecipeRepository;
import pl.kkowalewski.recipeapp.repository.UnitOfMeasureRepository;

import java.util.Optional;

@Service
public class IngredientServiceImpl implements IngredientService {

    /*------------------------ FIELDS REGION ------------------------*/
    private final IngredientToIngredientCommand ingredientToIngredientCommand;
    private final IngredientCommandToIngredient ingredientCommandToIngredient;
    private final RecipeRepository recipeRepository;
    private final UnitOfMeasureRepository unitOfMeasureRepository;

    /*------------------------ METHODS REGION ------------------------*/
    public IngredientServiceImpl(IngredientToIngredientCommand ingredientToIngredientCommand,
                                 IngredientCommandToIngredient ingredientCommandToIngredient,
                                 RecipeRepository recipeRepository,
                                 UnitOfMeasureRepository unitOfMeasureRepository) {
        this.ingredientToIngredientCommand = ingredientToIngredientCommand;
        this.ingredientCommandToIngredient = ingredientCommandToIngredient;
        this.recipeRepository = recipeRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
    }

    @Override
    public IngredientCommand findByRecipeIdAndIngredientId(Long recipeId, Long ingredientId) {
        Optional<Recipe> recipeOptional = recipeRepository.findById(recipeId);

        Recipe recipe = recipeOptional.get();

        Optional<IngredientCommand> ingredientCommandOptional = recipe.getIngredients().stream()
                .filter(ingredient -> ingredient.getId().equals(ingredientId))
                .map(ingredient -> ingredientToIngredientCommand.convert(ingredient)).findFirst();

        return ingredientCommandOptional.get();
    }

    @Override
    public IngredientCommand saveIngredientCommand(IngredientCommand ingredientCommand) {
        Optional<Recipe> recipeOptional =
                recipeRepository.findById(ingredientCommand.getRecipeId());

        Recipe recipe = recipeOptional.get();

        if (recipeOptional.isPresent()) {
            Optional<Ingredient> ingredientOptional = recipe
                    .getIngredients()
                    .stream()
                    .filter(ingredient -> ingredient.getId().equals(ingredientCommand.getId()))
                    .findFirst();

            if (ingredientOptional.isPresent()) {
                Ingredient ingredient = ingredientOptional.get();
                ingredient.setDescription(ingredientCommand.getDescription());
                ingredient.setAmount(ingredientCommand.getAmount());
                ingredient.setUom(unitOfMeasureRepository.findById(ingredientCommand.getUom().getId())
                        .orElseThrow(() -> new UnitOfMeasureNotFoundException()));
            } else {
                recipe.addIngredient(ingredientCommandToIngredient.convert(ingredientCommand));
            }
        } else {
            return new IngredientCommand();
        }

        return ingredientToIngredientCommand.convert(recipeRepository.save(recipe).getIngredients().stream()
                .filter(recipeIngredients -> recipeIngredients.getId().equals(ingredientCommand.getId()))
                .findFirst()
                .get());
    }
}
