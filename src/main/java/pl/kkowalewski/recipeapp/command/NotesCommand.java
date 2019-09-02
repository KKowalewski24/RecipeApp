package pl.kkowalewski.recipeapp.command;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NotesCommand {

    /*------------------------ FIELDS REGION ------------------------*/
    private Long id;
    private String recipeNotes;

    /*------------------------ METHODS REGION ------------------------*/

}
