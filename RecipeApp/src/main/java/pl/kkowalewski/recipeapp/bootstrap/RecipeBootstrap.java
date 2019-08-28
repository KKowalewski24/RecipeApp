package pl.kkowalewski.recipeapp.bootstrap;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import pl.kkowalewski.recipeapp.bundle.GuacamoleNotes;
import pl.kkowalewski.recipeapp.bundle.GuacamoleRecipe;
import pl.kkowalewski.recipeapp.bundle.TacosNotes;
import pl.kkowalewski.recipeapp.bundle.TacosRecipe;
import pl.kkowalewski.recipeapp.exception.CategoryException;
import pl.kkowalewski.recipeapp.exception.UnitsException;
import pl.kkowalewski.recipeapp.model.*;
import pl.kkowalewski.recipeapp.repository.CategoryRepository;
import pl.kkowalewski.recipeapp.repository.RecipeRepository;
import pl.kkowalewski.recipeapp.repository.UnitOfMeasureRepository;

import java.math.BigDecimal;
import java.util.*;

@Component
public class RecipeBootstrap implements ApplicationListener<ContextRefreshedEvent> {

    /*------------------------ FIELDS REGION ------------------------*/
    public static final Long RECIPE_ID_ONE = 1L;
    public static final Long RECIPE_ID_TWO = 2L;

    private final CategoryRepository categoryRepository;
    private final RecipeRepository recipeRepository;
    private final UnitOfMeasureRepository unitOfMeasureRepository;

    private GuacamoleRecipe guacamoleRecipeBundle = new GuacamoleRecipe();
    private GuacamoleNotes guacamoleNotesBundle = new GuacamoleNotes();
    private TacosRecipe tacosRecipeBundle = new TacosRecipe();
    private TacosNotes tacosNotesBundle = new TacosNotes();

    private UnitOfMeasure eachUom;
    private UnitOfMeasure tableSpoonUom;
    private UnitOfMeasure teapoonUom;
    private UnitOfMeasure dashUom;
    private UnitOfMeasure pintUom;
    private UnitOfMeasure cupsUom;

    private Category americanCategory;
    private Category mexicanCategory;

    private Recipe guacamoleRecipe;
    private Notes guacamoleNotes;
    private Recipe tacosRecipe;
    private Notes tacosNotes;

    /*------------------------ METHODS REGION ------------------------*/
    public RecipeBootstrap(CategoryRepository categoryRepository,
                           RecipeRepository recipeRepository,
                           UnitOfMeasureRepository unitOfMeasureRepository) {
        this.categoryRepository = categoryRepository;
        this.recipeRepository = recipeRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
    }

    private Optional<UnitOfMeasure> checkUnits(String description) {
        Optional<UnitOfMeasure> unit = unitOfMeasureRepository.findByDescription(description);

        if (!unit.isPresent()) {
            throw new UnitsException();
        }

        return unit;
    }

    private Optional<Category> checkCategory(String description) {
        Optional<Category> category = categoryRepository.findByDescription(description);

        if (!category.isPresent()) {
            throw new CategoryException();
        }

        return category;
    }

    private Set<Category> prepareCategoriesSet(Category... category) {
        Set<Category> categories = new HashSet<>();

        for (Category it : category) {
            categories.add(it);
        }

        return categories;
    }

    private Set<Ingredient> prepareGuacamoleIngredientsSet() {
        Set<Ingredient> ingredients = new HashSet<>();

        ingredients.add(new Ingredient("ripe avocados",
                new BigDecimal(2), eachUom, guacamoleRecipe));
        ingredients.add(new Ingredient("Kosher salt",
                new BigDecimal(".5"), teapoonUom, guacamoleRecipe));
        ingredients.add(new Ingredient("fresh lime juice or lemon juice",
                new BigDecimal(2), tableSpoonUom, guacamoleRecipe));
        ingredients.add(new Ingredient("minced red onion or thinly sliced green onion",
                new BigDecimal(2), tableSpoonUom, guacamoleRecipe));
        ingredients.add(new Ingredient("serrano chiles, stems and seeds removed, minced",
                new BigDecimal(2), eachUom, guacamoleRecipe));
        ingredients.add(new Ingredient("Cilantro",
                new BigDecimal(2), tableSpoonUom, guacamoleRecipe));
        ingredients.add(new Ingredient("freshly grated black pepper",
                new BigDecimal(2), dashUom, guacamoleRecipe));
        ingredients.add(new Ingredient("ripe tomato, seeds and pulp removed, chopped",
                new BigDecimal(".5"), eachUom, guacamoleRecipe));

        return ingredients;
    }

    private Set<Ingredient> prepareTacosIngredientsSet() {
        Set<Ingredient> ingredients = new HashSet<>();

        ingredients.add(new Ingredient("Ancho Chili Powder",
                new BigDecimal(2), tableSpoonUom, tacosRecipe));
        ingredients.add(new Ingredient("Dried Oregano",
                new BigDecimal(1), teapoonUom, tacosRecipe));
        ingredients.add(new Ingredient("Dried Cumin",
                new BigDecimal(1), teapoonUom, tacosRecipe));
        ingredients.add(new Ingredient("Sugar",
                new BigDecimal(1), teapoonUom, tacosRecipe));
        ingredients.add(new Ingredient("Salt",
                new BigDecimal(".5"), teapoonUom, tacosRecipe));
        ingredients.add(new Ingredient("Clove of Garlic, Choppedr",
                new BigDecimal(1), eachUom, tacosRecipe));
        ingredients.add(new Ingredient("finely grated orange zestr",
                new BigDecimal(1), tableSpoonUom, tacosRecipe));
        ingredients.add(new Ingredient("fresh-squeezed orange juice",
                new BigDecimal(3), tableSpoonUom, tacosRecipe));
        ingredients.add(new Ingredient("Olive Oil",
                new BigDecimal(2), tableSpoonUom, tacosRecipe));
        ingredients.add(new Ingredient("boneless chicken thighs",
                new BigDecimal(4), tableSpoonUom, tacosRecipe));
        ingredients.add(new Ingredient("small corn tortillasr",
                new BigDecimal(8), eachUom, tacosRecipe));
        ingredients.add(new Ingredient("packed baby arugula",
                new BigDecimal(3), cupsUom, tacosRecipe));
        ingredients.add(new Ingredient("medium ripe avocados, slic",
                new BigDecimal(2), eachUom, tacosRecipe));
        ingredients.add(new Ingredient("radishes, thinly sliced",
                new BigDecimal(4), eachUom, tacosRecipe));
        ingredients.add(new Ingredient("cherry tomatoes, halved",
                new BigDecimal(".5"), pintUom, tacosRecipe));
        ingredients.add(new Ingredient("red onion, thinly sliced",
                new BigDecimal(".25"), eachUom, tacosRecipe));
        ingredients.add(new Ingredient("Roughly chopped cilantro",
                new BigDecimal(4), eachUom, tacosRecipe));
        ingredients.add(new Ingredient("cup sour cream thinned with 1/4 cup milk",
                new BigDecimal(4), cupsUom, tacosRecipe));
        ingredients.add(new Ingredient("lime, cut into wedges",
                new BigDecimal(4), eachUom, tacosRecipe));

        return ingredients;
    }

    private List<Recipe> prepareRecipesList() {
        List<Recipe> recipes = new ArrayList<>();

        /*------------------------ CHECK ------------------------*/
        eachUom = checkUnits("Each").get();
        tableSpoonUom = checkUnits("Tablespoon").get();
        teapoonUom = checkUnits("Teaspoon").get();
        dashUom = checkUnits("Dash").get();
        pintUom = checkUnits("Pint").get();
        cupsUom = checkUnits("Cup").get();

        americanCategory = checkCategory("American").get();
        mexicanCategory = checkCategory("Mexican").get();

        /*------------------------ GUACAMOLE------------------------*/
        guacamoleRecipe = new Recipe(RECIPE_ID_ONE,
                guacamoleRecipeBundle.getObject("_description").toString(),
                guacamoleRecipeBundle.getObject("_directions").toString(),
                Integer.parseInt(guacamoleRecipeBundle.getObject("_prepTime").toString()),
                Integer.parseInt(guacamoleRecipeBundle.getObject("_cookTime").toString()),
                new Notes(guacamoleRecipe,
                        guacamoleNotesBundle.getObject("_description").toString()),
                Difficulty.EASY, prepareGuacamoleIngredientsSet(),
                prepareCategoriesSet(americanCategory, mexicanCategory));

        guacamoleNotes = guacamoleRecipe.getNotes();

        /*------------------------ TACOS ------------------------*/
        tacosRecipe = new Recipe(RECIPE_ID_TWO,
                tacosRecipeBundle.getObject("_description").toString(),
                tacosRecipeBundle.getObject("_directions").toString(),
                Integer.parseInt(tacosRecipeBundle.getObject("_prepTime").toString()),
                Integer.parseInt(tacosRecipeBundle.getObject("_cookTime").toString()),
                new Notes(tacosRecipe, tacosNotesBundle.getObject("_description").toString()),
                Difficulty.MEDIUM, prepareTacosIngredientsSet(),
                prepareCategoriesSet(americanCategory, mexicanCategory));

        tacosNotes = tacosRecipe.getNotes();

        /*------------------------ ADD TO SET ------------------------*/
        recipes.add(guacamoleRecipe);
        recipes.add(tacosRecipe);

        return recipes;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        recipeRepository.saveAll(prepareRecipesList());
    }
}
