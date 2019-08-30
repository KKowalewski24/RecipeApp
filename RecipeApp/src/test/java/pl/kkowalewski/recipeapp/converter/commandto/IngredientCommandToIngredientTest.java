package pl.kkowalewski.recipeapp.converter.commandto;

import org.junit.Before;
import org.junit.Test;
import pl.kkowalewski.recipeapp.command.IngredientCommand;
import pl.kkowalewski.recipeapp.command.UnitOfMeasureCommand;
import pl.kkowalewski.recipeapp.model.Ingredient;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public class IngredientCommandToIngredientTest {

    /*------------------------ FIELDS REGION ------------------------*/
    private static final Long ID = 1L;
    private static final Long UNIT_ID = 2L;
    private static final BigDecimal AMOUNT = new BigDecimal("1");
    private static final String DESCRIPTION = "abc";
    private static final String UNIT_DESCRIPTION = "cde";

    private IngredientCommandToIngredient converter;

    /*------------------------ METHODS REGION ------------------------*/
    @Before
    public void setUp() {
        converter = new IngredientCommandToIngredient(new UnitOfMeasureCommandToUnitOfMeasure());
    }

    @Test
    public void NullObjectTest() {
        assertNull(converter.convert(null));
    }

    @Test
    public void emptyObjectTest() {
        assertNotNull(converter.convert(new IngredientCommand()));
    }

    @Test
    public void convertTest() {
        Ingredient ingredient = converter.convert(new IngredientCommand(ID, DESCRIPTION, AMOUNT,
                new UnitOfMeasureCommand(UNIT_ID, UNIT_DESCRIPTION)));

        assertNotNull(ingredient);
        assertNotNull(ingredient.getUom());
        assertEquals(ID, ingredient.getId());
        assertEquals(AMOUNT, ingredient.getAmount());
        assertEquals(DESCRIPTION, ingredient.getDescription());
        assertEquals(UNIT_ID, ingredient.getUom().getId());
        assertEquals(UNIT_DESCRIPTION, ingredient.getUom().getDescription());
    }

    @Test
    public void convertUnitNullTest() {
        Ingredient ingredient = converter.convert(new IngredientCommand(ID, DESCRIPTION, AMOUNT,
                null));

        assertNotNull(ingredient);
        assertNull(ingredient.getUom());
        assertEquals(ID, ingredient.getId());
        assertEquals(AMOUNT, ingredient.getAmount());
        assertEquals(DESCRIPTION, ingredient.getDescription());
    }
}
    