package pl.kkowalewski.recipeapp.service.ingredient;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import pl.kkowalewski.recipeapp.command.IngredientCommand;
import pl.kkowalewski.recipeapp.converter.commandto.IngredientCommandToIngredient;
import pl.kkowalewski.recipeapp.converter.commandto.UnitOfMeasureCommandToUnitOfMeasure;
import pl.kkowalewski.recipeapp.converter.tocommand.IngredientToIngredientCommand;
import pl.kkowalewski.recipeapp.converter.tocommand.UnitOfMeasureToUnitOfMeasureCommand;
import pl.kkowalewski.recipeapp.model.Ingredient;
import pl.kkowalewski.recipeapp.model.Recipe;
import pl.kkowalewski.recipeapp.repository.RecipeRepository;
import pl.kkowalewski.recipeapp.repository.UnitOfMeasureRepository;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
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
    private final IngredientCommandToIngredient ingredientCommandToIngredient;

    @Mock
    private RecipeRepository recipeRepository;

    @Mock
    private UnitOfMeasureRepository unitOfMeasureRepository;

    private IngredientServiceImpl ingredientService;

    /*------------------------ METHODS REGION ------------------------*/
    public IngredientServiceImplTest() {
        this.ingredientToIngredientCommand =
                new IngredientToIngredientCommand(new UnitOfMeasureToUnitOfMeasureCommand());
        this.ingredientCommandToIngredient =
                new IngredientCommandToIngredient(new UnitOfMeasureCommandToUnitOfMeasure());
    }

    @Before
    public void setUp() {
        ingredientService = new IngredientServiceImpl(
                ingredientToIngredientCommand, ingredientCommandToIngredient,
                recipeRepository, unitOfMeasureRepository);
    }

    private Recipe prepareRecipe(Long id) {
        return new Recipe(id);
    }

    private IngredientCommand prepareIngredientCommand(Long id, Long recipeId) {
        return new IngredientCommand(id, recipeId);
    }

    private Ingredient prepareIngredient(Long id) {
        return new Ingredient(id);
    }

    private Ingredient prepareIngredient(Long id, Recipe recipe) {
        Ingredient ingredient = new Ingredient(id);
        ingredient.setRecipe(recipe);

        return ingredient;
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

    @Test
    public void saveIngredientCommandTest() {
        Recipe recipe = new Recipe(RECIPE_ID);
        recipe.addIngredient(new Ingredient());
        recipe.getIngredients().iterator().next().setId(ING_THREE_ID);

        when(recipeRepository.findById(anyLong())).thenReturn(Optional.of(new Recipe()));
        when(recipeRepository.save(any())).thenReturn(recipe);

        assertEquals(Long.valueOf(ING_THREE_ID), ingredientService.saveIngredientCommand(
                prepareIngredientCommand(ING_THREE_ID, RECIPE_ID)).getId());
        verify(recipeRepository).findById(anyLong());
        verify(recipeRepository).save(any(Recipe.class));
    }

    @Test
    public void deleteByIdTest() {
        Recipe recipe = prepareRecipe(RECIPE_ID);
        recipe.addIngredient(prepareIngredient(ING_THREE_ID, recipe));
        Optional<Recipe> recipeOptional = Optional.of(recipe);

        when(recipeRepository.findById(anyLong())).thenReturn(recipeOptional);

        ingredientService.deleteById(RECIPE_ID, ING_THREE_ID);
        verify(recipeRepository).findById(anyLong());
        verify(recipeRepository).save(any(Recipe.class));
    }
}
