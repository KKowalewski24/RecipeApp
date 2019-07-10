package pl.kkowalewski.recipeapp.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.util.Set;

@Entity
public class Recipe {

    /*------------------------ FIELDS REGION ------------------------*/
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;
    private String source;
    private String url;
    private String direction;
    private Integer preparationTime;
    private Integer cockTime;
    private Integer servings;
    @Lob
    private Byte[] image;
    @OneToOne(cascade = CascadeType.ALL)
    private Notes notes;
    @Enumerated(value = EnumType.STRING)
    private Difficulty difficulty;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "recipe")
    private Set<Ingredient> ingredients;
    @ManyToMany
    private Set<Category> categories;

    /*------------------------ METHODS REGION ------------------------*/

    public Recipe(String description, String source, String url, String direction,
                  Integer preparationTime, Integer cockTime, Integer servings, Byte[] image,
                  Notes notes, Difficulty difficulty, Set<Ingredient> ingredients,
                  Set<Category> categories) {
        this.description = description;
        this.source = source;
        this.url = url;
        this.direction = direction;
        this.preparationTime = preparationTime;
        this.cockTime = cockTime;
        this.servings = servings;
        this.image = image;
        this.notes = notes;
        this.difficulty = difficulty;
        this.ingredients = ingredients;
        this.categories = categories;
    }

    public Long getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public String getSource() {
        return source;
    }

    public String getUrl() {
        return url;
    }

    public String getDirection() {
        return direction;
    }

    public Integer getPreparationTime() {
        return preparationTime;
    }

    public Integer getCockTime() {
        return cockTime;
    }

    public Integer getServings() {
        return servings;
    }

    public Byte[] getImage() {
        return image;
    }

    public Notes getNotes() {
        return notes;
    }

    public Difficulty getDifficulty() {
        return difficulty;
    }

    public Set<Ingredient> getIngredients() {
        return ingredients;
    }

    public Set<Category> getCategories() {
        return categories;
    }
}
