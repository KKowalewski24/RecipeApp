package pl.kkowalewski.recipeapp.command;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.kkowalewski.recipeapp.model.Difficulty;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RecipeCommand {

    /*------------------------ FIELDS REGION ------------------------*/
    private Long id;
    private String description;
    private Integer prepTime;
    private Integer cookTime;
    private Integer servings;
    private String source;
    private String url;
    private String directions;
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
