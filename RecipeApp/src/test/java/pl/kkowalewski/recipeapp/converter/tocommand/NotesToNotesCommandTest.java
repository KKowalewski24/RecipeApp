package pl.kkowalewski.recipeapp.converter.tocommand;

import org.junit.Before;
import org.junit.Test;
import pl.kkowalewski.recipeapp.command.NotesCommand;
import pl.kkowalewski.recipeapp.model.Notes;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public class NotesToNotesCommandTest {

    /*------------------------ FIELDS REGION ------------------------*/
    private static final Long ID = 1L;
    private static final String NOTES = "ABC";

    private NotesToNotesCommand converter;

    /*------------------------ METHODS REGION ------------------------*/
    @Before
    public void setUp() {
        converter = new NotesToNotesCommand();
    }

    @Test
    public void NullObjectTest() {
        assertNull(converter.convert(null));
    }

    @Test
    public void emptyObjectTest() {
        assertNotNull(converter.convert(new Notes()));
    }

    @Test
    public void convertTest() {
        NotesCommand notesCommand = converter.convert(new Notes(ID, NOTES));

        assertNotNull(notesCommand);
        assertEquals(ID, notesCommand.getId());
        assertEquals(NOTES, notesCommand.getRecipeNotes());
    }
}
    