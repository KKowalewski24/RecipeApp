package pl.kkowalewski.recipeapp.converter.commandto;

import org.junit.Before;
import org.junit.Test;
import pl.kkowalewski.recipeapp.command.NotesCommand;
import pl.kkowalewski.recipeapp.model.Notes;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public class NotesCommandToNotesTest {

    /*------------------------ FIELDS REGION ------------------------*/
    private static final Long ID = 1L;
    private static final String NOTES = "ABC";

    private NotesCommandToNotes converter;

    /*------------------------ METHODS REGION ------------------------*/
    @Before
    public void setUp() {
        converter = new NotesCommandToNotes();
    }

    @Test
    public void NullObjectTest() {
        assertNull(converter.convert(null));
    }

    @Test
    public void emptyObjectTest() {
        assertNotNull(converter.convert(new NotesCommand()));
    }

    @Test
    public void convertTest() {
        Notes notes = converter.convert(new NotesCommand(ID, NOTES));

        assertNotNull(notes);
        assertEquals(ID, notes.getId());
        assertEquals(NOTES, notes.getRecipeNotes());
    }
}
    