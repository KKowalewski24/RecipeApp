package pl.kkowalewski.recipeapp.converter.tocommand;

import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import pl.kkowalewski.recipeapp.command.NotesCommand;
import pl.kkowalewski.recipeapp.converter.Command;
import pl.kkowalewski.recipeapp.model.Notes;

@Component
public class NotesToNotesCommand implements Converter<Notes, NotesCommand>,
        Command<Notes, NotesCommand> {

    /*------------------------ FIELDS REGION ------------------------*/

    /*------------------------ METHODS REGION ------------------------*/
    @Synchronized
    @Nullable
    @Override
    public NotesCommand convert(Notes notes) {
        if (notes == null) {
            return null;
        }

        return new NotesCommand(notes.getId(), notes.getRecipeNotes());
    }
}
