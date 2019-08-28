package pl.kkowalewski.recipeapp.converter.commandto;

import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import pl.kkowalewski.recipeapp.command.NotesCommand;
import pl.kkowalewski.recipeapp.converter.Command;
import pl.kkowalewski.recipeapp.model.Notes;

@Component
public class NotesCommandToNotes implements Converter<NotesCommand, Notes>,
        Command<NotesCommand, Notes> {

    /*------------------------ FIELDS REGION ------------------------*/

    /*------------------------ METHODS REGION ------------------------*/
    @Synchronized
    @Nullable
    @Override
    public Notes convert(NotesCommand notesCommand) {
        if (notesCommand == null) {
            return null;
        }

        return new Notes(notesCommand.getId(), notesCommand.getRecipeNotes());
    }
}
