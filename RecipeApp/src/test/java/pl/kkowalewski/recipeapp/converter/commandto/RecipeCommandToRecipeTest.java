package pl.kkowalewski.recipeapp.converter.commandto;

import org.junit.Before;
import org.junit.Test;
import pl.kkowalewski.recipeapp.command.CategoryCommand;
import pl.kkowalewski.recipeapp.command.IngredientCommand;
import pl.kkowalewski.recipeapp.command.NotesCommand;
import pl.kkowalewski.recipeapp.command.RecipeCommand;
import pl.kkowalewski.recipeapp.model.Difficulty;
import pl.kkowalewski.recipeapp.model.Recipe;

import java.util.Arrays;
import java.util.HashSet;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public class RecipeCommandToRecipeTest {

    /*------------------------ FIELDS REGION ------------------------*/
    private static final Long RECIPE_ID = 1L;
    private static final Integer PREP_TIME = Integer.valueOf("7");
    private static final Integer COOK_TIME = Integer.valueOf("5");
    private static final String DESCRIPTION = "My Recipe";
    private static final String DIRECTIONS = "Directions";
    private static final Difficulty DIFFICULTY = Difficulty.EASY;
    private static final Integer SERVINGS = Integer.valueOf("3");
    private static final String SOURCE = "Source";
    private static final String URL = "Some URL";
    private static final Long NOTES_ID = 9L;
    private static final String NOTES_DESCRIPTION = "ABC";

    private RecipeCommandToRecipe converter;

    /*------------------------ METHODS REGION ------------------------*/
    @Before
    public void setUp() {
        converter = new RecipeCommandToRecipe(
                new CategoryCommandToCategory(),
                new IngredientCommandToIngredient(new UnitOfMeasureCommandToUnitOfMeasure()),
                new NotesCommandToNotes());
    }

    @Test
    public void NullObjectTest() {
        assertNull(converter.convert(null));
    }

    @Test
    public void emptyObjectTest() {
        assertNotNull(converter.convert(new RecipeCommand()));
    }

    @Test
    public void convertTest() {
        Recipe recipe = converter.convert(new RecipeCommand(RECIPE_ID, PREP_TIME, COOK_TIME,
                DESCRIPTION, DIFFICULTY, DIRECTIONS, SERVINGS, SOURCE, URL,
                new NotesCommand(NOTES_ID, NOTES_DESCRIPTION),
                new HashSet<IngredientCommand>(Arrays.asList(
                        new IngredientCommand(),
                        new IngredientCommand()
                )),
                new HashSet<CategoryCommand>(Arrays.asList(
                        new CategoryCommand(),
                        new CategoryCommand()
                ))
        ));

        assertNotNull(recipe);
        assertEquals(RECIPE_ID, recipe.getId());
        assertEquals(COOK_TIME, recipe.getCookTime());
        assertEquals(PREP_TIME, recipe.getPrepTime());
        assertEquals(DESCRIPTION, recipe.getDescription());
        assertEquals(DIFFICULTY, recipe.getDifficulty());
        assertEquals(DIRECTIONS, recipe.getDirections());
        assertEquals(SERVINGS, recipe.getServings());
        assertEquals(SOURCE, recipe.getSource());
        assertEquals(URL, recipe.getUrl());
        assertEquals(NOTES_ID, recipe.getNotes().getId());
    }
}
    