package pl.kkowalewski.recipeapp.command;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.URL;
import pl.kkowalewski.recipeapp.model.Difficulty;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RecipeCommand {

    /*------------------------ FIELDS REGION ------------------------*/
    private Long id;

    @NotBlank
    @Size(min = 3, max = 255)
    private String description;

    @Min(1)
    @Max(999)
    private Integer prepTime;

    @Min(1)
    @Max(999)
    private Integer cookTime;

    @Min(1)
    @Max(100)
    private Integer servings;

    @URL
    private String url;

    @NotBlank
    private String directions;

    private String source;
    private Set<IngredientCommand> ingredients = new HashSet<>();
    private Byte[] image;
    private Difficulty difficulty;
    private NotesCommand notes;
    private Set<CategoryCommand> categories = new HashSet<>();

    /*------------------------ METHODS REGION ------------------------*/
    public RecipeCommand(Long id) {
        this.id = id;
    }

    public RecipeCommand(Long id, Integer prepTime, Integer cookTime, String description,
                         Difficulty difficulty, String directions, Integer servings,
                         String source, String url, NotesCommand notes) {
        this.id = id;
        this.prepTime = prepTime;
        this.cookTime = cookTime;
        this.description = description;
        this.difficulty = difficulty;
        this.directions = directions;
        this.servings = servings;
        this.source = source;
        this.url = url;
        this.notes = notes;
    }

    public RecipeCommand(Long id, Integer prepTime, Integer cookTime, String description,
                         Difficulty difficulty, String directions, Integer servings, String source,
                         String url, NotesCommand notes,
                         HashSet<IngredientCommand> ingredients,
                         HashSet<CategoryCommand> categories) {
        this.id = id;
        this.prepTime = prepTime;
        this.cookTime = cookTime;
        this.description = description;
        this.difficulty = difficulty;
        this.directions = directions;
        this.servings = servings;
        this.source = source;
        this.url = url;
        this.notes = notes;
        this.ingredients = ingredients;
        this.categories = categories;
    }

    public RecipeCommand(Long id, Integer prepTime, Integer cookTime, String description,
                         Difficulty difficulty, String directions, Integer servings,
                         String source, String url, NotesCommand notes, Byte[] image) {
        this.id = id;
        this.prepTime = prepTime;
        this.cookTime = cookTime;
        this.description = description;
        this.difficulty = difficulty;
        this.directions = directions;
        this.servings = servings;
        this.source = source;
        this.url = url;
        this.notes = notes;
        this.image = image;
    }
}
