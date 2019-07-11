package pl.kkowalewski.recipeapp.bootstrap;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import pl.kkowalewski.recipeapp.bundle.*;
import pl.kkowalewski.recipeapp.exception.CategoryException;
import pl.kkowalewski.recipeapp.exception.UnitsException;
import pl.kkowalewski.recipeapp.model.*;
import pl.kkowalewski.recipeapp.repository.CategoryRepository;
import pl.kkowalewski.recipeapp.repository.RecipeRepository;
import pl.kkowalewski.recipeapp.repository.UnitOfMeasureRepository;

import java.util.*;

@Component
public class RecipeBootstrap implements ApplicationListener<ContextRefreshedEvent> {

    /*------------------------ FIELDS REGION ------------------------*/
    private final CategoryRepository categoryRepository;
    private final RecipeRepository recipeRepository;
    private final UnitOfMeasureRepository unitOfMeasureRepository;

    private GuacamoleRecipe guacamoleRecipeBundle = new GuacamoleRecipe();
    private GuacamoleNotes guacamoleNotesBundle = new GuacamoleNotes();
    private TacosRecipe tacosRecipeBundle = new TacosRecipe();
    private TacosNotes tacosNotesBundle = new TacosNotes();
    private GuacamoleIngredient guacamoleIngredientBundle = new GuacamoleIngredient();
    private TacosIngredient tacosIngredientBundle = new TacosIngredient();

    /*------------------------ METHODS REGION ------------------------*/
    public RecipeBootstrap(CategoryRepository categoryRepository,
                           RecipeRepository recipeRepository,
                           UnitOfMeasureRepository unitOfMeasureRepository) {
        this.categoryRepository = categoryRepository;
        this.recipeRepository = recipeRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
    }

    public Optional<UnitOfMeasure> checkUnits(String description) {
        Optional<UnitOfMeasure> unit = unitOfMeasureRepository.findByDescription(description);

        if (!unit.isPresent()) {
            throw new UnitsException();
        }

        return unit;
    }

    public Optional<Category> checkCategory(String description) {
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

    private List<Recipe> prepareRecipesList() {
        List<Recipe> recipes = new ArrayList<>();

        /*------------------------ CHECK ------------------------*/
        UnitOfMeasure eachUom = checkUnits("Each").get();
        UnitOfMeasure tableSpoonUom = checkUnits("Tablespoon").get();
        UnitOfMeasure teapoonUom = checkUnits("Teaspoon").get();
        UnitOfMeasure dashUom = checkUnits("Dash").get();
        UnitOfMeasure pintUom = checkUnits("Pint").get();
        UnitOfMeasure cupsUom = checkUnits("Cup").get();

        Category americanCategory = checkCategory("American").get();
        Category mexicanCategory = checkCategory("Mexican").get();

        /*------------------------ GUACAMOLE------------------------*/
        Notes guacamoleNotes = new Notes();
        Recipe guacamoleRecipe = new Recipe(
                guacamoleRecipeBundle.getObject("_description").toString(),
                guacamoleRecipeBundle.getObject("_directions").toString(),
                Integer.parseInt(guacamoleRecipeBundle.getObject("_prepTime").toString()),
                Integer.parseInt(guacamoleRecipeBundle.getObject("_cookTime").toString()),
                guacamoleNotes, Difficulty.EASY, guacamoleIngredientBundle.ingredients,
                prepareCategoriesSet(americanCategory,
                        mexicanCategory));

        guacamoleNotes.setRecipe(guacamoleRecipe);
        guacamoleNotes.setRecipeNotes(guacamoleNotesBundle.getObject("_description").toString());

        /*------------------------ TACOS ------------------------*/
        Notes tacosNotes = new Notes();
        Recipe tacosRecipe = new Recipe(
                tacosRecipeBundle.getObject("_description").toString(),
                tacosRecipeBundle.getObject("_directions").toString(),
                Integer.parseInt(tacosRecipeBundle.getObject("_prepTime").toString()),
                Integer.parseInt(tacosRecipeBundle.getObject("_cookTime").toString()),
                guacamoleNotes, Difficulty.MEDIUM, tacosIngredientBundle.ingredients,
                prepareCategoriesSet(americanCategory,
                        mexicanCategory));

        tacosNotes.setRecipe(tacosRecipe);
        tacosNotes.setRecipeNotes(tacosNotesBundle.getObject("_description").toString());

        /*------------------------ ADD TO SET ------------------------*/
        recipes.add(guacamoleRecipe);
        recipes.add(tacosRecipe);

        return recipes;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        //recipeRepository.saveAll(prepareRecipesList());
    }
}
