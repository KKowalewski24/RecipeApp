package pl.kkowalewski.recipeapp.converter.tocommand;

import org.junit.Before;
import org.junit.Test;
import pl.kkowalewski.recipeapp.command.IngredientCommand;
import pl.kkowalewski.recipeapp.model.Ingredient;
import pl.kkowalewski.recipeapp.model.Recipe;
import pl.kkowalewski.recipeapp.model.UnitOfMeasure;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public class IngredientToIngredientCommandTest {

    /*------------------------ FIELDS REGION ------------------------*/
    private static final Long ID = 1L;
    private static final Long UNIT_ID = 2L;
    private static final BigDecimal AMOUNT = new BigDecimal("1");
    private static final String DESCRIPTION = "abc";
    private static final String UNIT_DESCRIPTION = "cde";
    private static final Recipe recipe = new Recipe();

    private IngredientToIngredientCommand converter;

    /*------------------------ METHODS REGION ------------------------*/
    @Before
    public void setUp() {
        converter = new IngredientToIngredientCommand(new UnitOfMeasureToUnitOfMeasureCommand());
    }

    @Test
    public void NullObjectTest() {
        assertNull(converter.convert(null));
    }

    @Test
    public void emptyObjectTest() {
        assertNotNull(converter.convert(new Ingredient()));
    }

    @Test
    public void convertTest() {
        IngredientCommand ingredientCommand = converter.convert(
                new Ingredient(ID, DESCRIPTION, AMOUNT,
                        new UnitOfMeasure(UNIT_ID, UNIT_DESCRIPTION), recipe));

        assertNotNull(ingredientCommand);
        assertNotNull(ingredientCommand.getUnitOfMeasure());
        assertEquals(ID, ingredientCommand.getId());
        assertEquals(AMOUNT, ingredientCommand.getAmount());
        assertEquals(DESCRIPTION, ingredientCommand.getDescription());
        assertEquals(UNIT_ID, ingredientCommand.getUnitOfMeasure().getId());
        assertEquals(UNIT_DESCRIPTION, ingredientCommand.getUnitOfMeasure().getDescription());
    }

    @Test
    public void convertUnitNullTest() {
        IngredientCommand ingredientCommand = converter.convert(
                new Ingredient(ID, DESCRIPTION, AMOUNT, null, recipe));

        assertNotNull(ingredientCommand);
        assertNull(ingredientCommand.getUnitOfMeasure());
        assertEquals(ID, ingredientCommand.getId());
        assertEquals(AMOUNT, ingredientCommand.getAmount());
        assertEquals(DESCRIPTION, ingredientCommand.getDescription());
    }
}
