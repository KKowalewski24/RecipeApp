package pl.kkowalewski.recipeapp.service.ingredient;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import pl.kkowalewski.recipeapp.command.IngredientCommand;
import pl.kkowalewski.recipeapp.converter.tocommand.IngredientToIngredientCommand;
import pl.kkowalewski.recipeapp.converter.tocommand.UnitOfMeasureToUnitOfMeasureCommand;
import pl.kkowalewski.recipeapp.model.Ingredient;
import pl.kkowalewski.recipeapp.model.Recipe;
import pl.kkowalewski.recipeapp.repository.RecipeRepository;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class IngredientServiceImplTest {

    /*------------------------ FIELDS REGION ------------------------*/
    private static final Long RECIPE_ID = 1L;
    private static final Long ING_ONE_ID = 1L;
    private static final Long ING_TWO_ID = 2L;
    private static final Long ING_THREE_ID = 3L;

    private final IngredientToIngredientCommand ingredientToIngredientCommand;

    @Mock
    private RecipeRepository recipeRepository;

    private IngredientServiceImpl ingredientService;

    /*------------------------ METHODS REGION ------------------------*/
    public IngredientServiceImplTest() {
        this.ingredientToIngredientCommand =
                new IngredientToIngredientCommand(new UnitOfMeasureToUnitOfMeasureCommand());
    }

    @Before
    public void setUp() {
        ingredientService = new IngredientServiceImpl(
                ingredientToIngredientCommand, recipeRepository);
    }

    private Recipe prepareRecipe(Long id) {
        return new Recipe(id);
    }

    private Ingredient prepareIngredient(Long id) {
        return new Ingredient(id);
    }

    @Test
    public void findByRecipeIdAndIngredientIdTest() {
        Recipe recipe = prepareRecipe(RECIPE_ID);

        recipe.addIngredient(prepareIngredient(ING_ONE_ID));
        recipe.addIngredient(prepareIngredient(ING_TWO_ID));
        recipe.addIngredient(prepareIngredient(ING_THREE_ID));

        Optional<Recipe> recipeOptional = Optional.of(recipe);

        when(recipeRepository.findById(anyLong())).thenReturn(recipeOptional);

        IngredientCommand ingredientCommand =
                ingredientService.findByRecipeIdAndIngredientId(RECIPE_ID, ING_THREE_ID);

        assertEquals(Long.valueOf(ING_THREE_ID), ingredientCommand.getId());
        assertEquals(Long.valueOf(RECIPE_ID), ingredientCommand.getRecipeId());
        verify(recipeRepository).findById(anyLong());
    }
}
    