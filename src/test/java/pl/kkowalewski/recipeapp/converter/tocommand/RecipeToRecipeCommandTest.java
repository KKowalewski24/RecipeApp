package pl.kkowalewski.recipeapp.converter.tocommand;

import org.junit.Before;
import org.junit.Test;
import pl.kkowalewski.recipeapp.command.RecipeCommand;
import pl.kkowalewski.recipeapp.model.Difficulty;
import pl.kkowalewski.recipeapp.model.Notes;
import pl.kkowalewski.recipeapp.model.Recipe;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public class RecipeToRecipeCommandTest {

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

    private RecipeToRecipeCommand converter;

    /*------------------------ METHODS REGION ------------------------*/
    @Before
    public void setUp() {
        converter = new RecipeToRecipeCommand(
                new CategoryToCategoryCommand(),
                new IngredientToIngredientCommand(new UnitOfMeasureToUnitOfMeasureCommand()),
                new NotesToNotesCommand());
    }

    @Test
    public void NullObjectTest() {
        assertNull(converter.convert(null));
    }

    @Test
    public void emptyObjectTest() {
        assertNotNull(converter.convert(new Recipe()));
    }

    @Test
    public void convertTest() {
        RecipeCommand recipeCommand = converter.convert(new Recipe(RECIPE_ID, PREP_TIME, COOK_TIME,
                DESCRIPTION, DIFFICULTY, DIRECTIONS, SERVINGS, SOURCE, URL,
                new Notes(NOTES_ID, NOTES_DESCRIPTION)));

        assertNotNull(recipeCommand);
        assertEquals(RECIPE_ID, recipeCommand.getId());
        assertEquals(COOK_TIME, recipeCommand.getCookTime());
        assertEquals(PREP_TIME, recipeCommand.getPrepTime());
        assertEquals(DESCRIPTION, recipeCommand.getDescription());
        assertEquals(DIFFICULTY, recipeCommand.getDifficulty());
        assertEquals(DIRECTIONS, recipeCommand.getDirections());
        assertEquals(SERVINGS, recipeCommand.getServings());
        assertEquals(SOURCE, recipeCommand.getSource());
        assertEquals(URL, recipeCommand.getUrl());
        assertEquals(NOTES_ID, recipeCommand.getNotes().getId());
    }
}
    