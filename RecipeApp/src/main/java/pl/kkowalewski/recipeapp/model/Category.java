package pl.kkowalewski.recipeapp.model;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.Set;

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
    public Category() {
    }

    public Category(final String description, final Set<Recipe> recipes) {
        this.description = description;
        this.recipes = recipes;
    }

    public Long getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public Set<Recipe> getRecipes() {
        return recipes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Category category = (Category) o;

        return new EqualsBuilder()
                .append(id, category.id)
                .append(description, category.description)
                .append(recipes, category.recipes)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(id)
                .append(description)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("id", id)
                .append("description", description)
                .append("recipes", recipes)
                .toString();
    }
}
