package pl.kkowalewski.recipeapp.converter.commandto;

import org.junit.Before;
import org.junit.Test;
import pl.kkowalewski.recipeapp.command.UnitOfMeasureCommand;
import pl.kkowalewski.recipeapp.model.UnitOfMeasure;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public class UnitOfMeasureCommandToUnitOfMeasureTest {

    /*------------------------ FIELDS REGION ------------------------*/
    public static final Long ID = 1L;
    public static final String DESCRIPTION = "description";

    private UnitOfMeasureCommandToUnitOfMeasure converter;

    /*------------------------ METHODS REGION ------------------------*/
    @Before
    public void setUp() {
        converter = new UnitOfMeasureCommandToUnitOfMeasure();
    }

    @Test
    public void NullObjectTest() {
        assertNull(converter.convert(null));
    }

    @Test
    public void emptyObjectTest() {
        assertNotNull(converter.convert(new UnitOfMeasureCommand()));
    }

    @Test
    public void convertTest() {
        UnitOfMeasure unit = converter.convert(new UnitOfMeasureCommand(ID, DESCRIPTION));

        assertNotNull(unit);
        assertEquals(ID, unit.getId());
        assertEquals(DESCRIPTION, unit.getDescription());
    }
}
    