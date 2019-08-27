package pl.kkowalewski.recipeapp.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@Data
@EqualsAndHashCode(exclude = {"recipe"})
@Entity
public class Notes {

    /*------------------------ FIELDS REGION ------------------------*/
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private Recipe recipe;

    @Lob
    private String recipeNotes;

    /*------------------------ METHODS REGION ------------------------*/
    public Notes() {
    }

    public Notes(Recipe recipe, String recipeNotes) {
        this.recipe = recipe;
        this.recipeNotes = recipeNotes;
    }
}
