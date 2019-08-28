package pl.kkowalewski.recipeapp.converter.tocommand;

import org.junit.Before;
import org.junit.Test;
import pl.kkowalewski.recipeapp.command.CategoryCommand;
import pl.kkowalewski.recipeapp.model.Category;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public class CategoryToCategoryCommandTest {

    /*------------------------ FIELDS REGION ------------------------*/
    private static final Long ID = 1L;
    private static final String DESCRIPTION = "description";

    private CategoryToCategoryCommand converter;

    /*------------------------ METHODS REGION ------------------------*/
    @Before
    public void setUp() {
        converter = new CategoryToCategoryCommand();
    }

    @Test
    public void NullObjectTest() {
        assertNull(converter.convert(null));
    }

    @Test
    public void emptyObjectTest() {
        assertNotNull(converter.convert(new Category()));
    }

    @Test
    public void convertTest() {
        CategoryCommand categoryCommand = converter.convert(new Category(ID, DESCRIPTION));

        assertEquals(ID, categoryCommand.getId());
        assertEquals(DESCRIPTION, categoryCommand.getDescription());
    }
}
    