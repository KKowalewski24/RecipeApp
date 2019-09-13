package pl.kkowalewski.recipeapp.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
public class Recipe {

    /*------------------------ FIELDS REGION ------------------------*/
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;
    private Integer prepTime;
    private Integer cookTime;
    private Integer servings;
    private String source;
    private String url;

    @Lob
    private String directions;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "recipe")
    private Set<Ingredient> ingredients = new HashSet<>();

    @Lob
    private Byte[] image;

    @Enumerated(value = EnumType.STRING)
    private Difficulty difficulty;

    @OneToOne(cascade = CascadeType.ALL)
    private Notes notes;

    @ManyToMany
    @JoinTable(name = "recipe_category",
            joinColumns = @JoinColumn(name = "recipe_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id"))
    private Set<Category> categories = new HashSet<>();

    /*------------------------ METHODS REGION ------------------------*/
    public Recipe() {
    }

    public Recipe(Long id) {
        this.id = id;
    }

    public Recipe(Long id, String description, String direction, Integer preparationTime,
                  Integer cockTime, Notes notes, Difficulty difficulty, Integer servings,
                  String source, String url, Set<Category> categories) {
        this.id = id;
        this.description = description;
        this.directions = direction;
        this.prepTime = preparationTime;
        this.cookTime = cockTime;
        this.notes = notes;
        this.difficulty = difficulty;
        this.servings = servings;
        this.source = source;
        this.url = url;
        this.categories = categories;
    }

    public Recipe(Long id, Integer cookTime, Integer prepTime, String description,
                  Difficulty difficulty, String directions, Integer servings,
                  String source, String url, Notes notes) {
        this.id = id;
        this.cookTime = cookTime;
        this.prepTime = prepTime;
        this.description = description;
        this.difficulty = difficulty;
        this.directions = directions;
        this.servings = servings;
        this.source = source;
        this.url = url;
        this.notes = notes;
    }

    public Recipe addIngredient(Ingredient ingredient) {
        ingredient.setRecipe(this);
        this.ingredients.add(ingredient);
        return this;
    }
}
