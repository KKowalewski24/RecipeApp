package pl.kkowalewski.recipeapp.model;

import lombok.Data;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.persistence.*;

@Data
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) return false;

        Notes notes = (Notes) o;

        return new EqualsBuilder()
                .append(id, notes.id)
                .append(recipeNotes, notes.recipeNotes)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(id)
                .append(recipeNotes)
                .toHashCode();
    }
}
