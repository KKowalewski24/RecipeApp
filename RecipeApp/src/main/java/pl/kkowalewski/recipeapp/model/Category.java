package pl.kkowalewski.recipeapp.model;

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
    private String description;
    @ManyToMany
    private Set<Recipe> recipes;

    /*------------------------ METHODS REGION ------------------------*/
    public Category(String description, Set<Recipe> recipes) {
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
}
