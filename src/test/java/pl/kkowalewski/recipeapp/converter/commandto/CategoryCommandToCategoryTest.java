package pl.kkowalewski.recipeapp.converter.commandto;

import org.junit.Before;
import org.junit.Test;
import pl.kkowalewski.recipeapp.command.CategoryCommand;
import pl.kkowalewski.recipeapp.model.Category;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public class CategoryCommandToCategoryTest {

    /*------------------------ FIELDS REGION ------------------------*/
    private static final Long ID = 1L;
    private static final String DESCRIPTION = "description";

    private CategoryCommandToCategory converter;

    /*------------------------ METHODS REGION ------------------------*/
    @Before
    public void setUp() {
        converter = new CategoryCommandToCategory();
    }

    @Test
    public void NullObjectTest() {
        assertNull(converter.convert(null));
    }

    @Test
    public void emptyObjectTest() {
        assertNotNull(converter.convert(new CategoryCommand()));
    }

    @Test
    public void convertTest() {
        Category category = converter.convert(new CategoryCommand(ID, DESCRIPTION));

        assertEquals(ID, category.getId());
        assertEquals(DESCRIPTION, category.getDescription());
    }
}
    