package pl.kkowalewski.recipeapp.model;

import lombok.Data;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
public class Category {

    /*------------------------ FIELDS REGION ------------------------*/
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToMany(mappedBy = "categories")
    private Set<Recipe> recipes;

    private String description;

    /*------------------------ METHODS REGION ------------------------*/

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) return false;

        Category category = (Category) o;

        return new EqualsBuilder()
                .append(id, category.id)
                .append(description, category.description)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(id)
                .append(description)
                .toHashCode();
    }
}
