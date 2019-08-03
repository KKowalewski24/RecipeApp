package pl.kkowalewski.recipeapp.model;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

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
    @Lob
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
    @JoinTable(name = "recipe_category",
            joinColumns = @JoinColumn(name = "recipe_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id"))
    private Set<Category> categories;

    /*------------------------ METHODS REGION ------------------------*/
//    public Recipe(final String description, final String source, final String url,
//                  final String direction, final Integer preparationTime, final Integer cockTime,
//                  final Integer servings, final Byte[] image, final Notes notes,
//                  final Difficulty difficulty, final Set<Ingredient> ingredients,
//                  final Set<Category> categories) {
//        this.description = description;
//        this.source = source;
//        this.url = url;
//        this.direction = direction;
//        this.preparationTime = preparationTime;
//        this.cockTime = cockTime;
//        this.servings = servings;
//        this.image = image;
//        this.notes = notes;
//        this.difficulty = difficulty;
//        this.ingredients = ingredients;
//        this.categories = categories;
//    }

    public Recipe() {
    }

    public Recipe(String description, String direction, Integer preparationTime, Integer cockTime,
                  Notes notes, Difficulty difficulty, Set<Ingredient> ingredients,
                  Set<Category> categories) {
        this.description = description;
        this.direction = direction;
        this.preparationTime = preparationTime;
        this.cockTime = cockTime;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Recipe recipe = (Recipe) o;

        return new EqualsBuilder()
                .append(id, recipe.id)
                .append(description, recipe.description)
                .append(source, recipe.source)
                .append(url, recipe.url)
                .append(direction, recipe.direction)
                .append(preparationTime, recipe.preparationTime)
                .append(cockTime, recipe.cockTime)
                .append(servings, recipe.servings)
                .append(image, recipe.image)
                .append(notes, recipe.notes)
                .append(difficulty, recipe.difficulty)
                .append(ingredients, recipe.ingredients)
                .append(categories, recipe.categories)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(id)
                .append(description)
                .append(source)
                .append(url)
                .append(direction)
                .append(preparationTime)
                .append(cockTime)
                .append(servings)
                .append(image)
                .append(notes)
                .append(difficulty)
                .append(ingredients)
                .append(categories)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("id", id)
                .append("description", description)
                .append("source", source)
                .append("url", url)
                .append("direction", direction)
                .append("preparationTime", preparationTime)
                .append("cockTime", cockTime)
                .append("servings", servings)
                .append("image", image)
                .append("notes", notes)
                .append("difficulty", difficulty)
                .append("ingredients", ingredients)
                .append("categories", categories)
                .toString();
    }
}
