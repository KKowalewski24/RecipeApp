package pl.kkowalewski.recipeapp.bootstrap;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import pl.kkowalewski.recipeapp.exception.CategoryException;
import pl.kkowalewski.recipeapp.exception.UnitsException;
import pl.kkowalewski.recipeapp.model.Category;
import pl.kkowalewski.recipeapp.model.Notes;
import pl.kkowalewski.recipeapp.model.Recipe;
import pl.kkowalewski.recipeapp.model.UnitOfMeasure;
import pl.kkowalewski.recipeapp.repository.CategoryRepository;
import pl.kkowalewski.recipeapp.repository.RecipeRepository;
import pl.kkowalewski.recipeapp.repository.UnitOfMeasureRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class RecipeBootstrap implements ApplicationListener<ContextRefreshedEvent> {

    /*------------------------ FIELDS REGION ------------------------*/
    private final CategoryRepository categoryRepository;
    private final RecipeRepository recipeRepository;
    private final UnitOfMeasureRepository unitOfMeasureRepository;

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

    private void prepareNotes() {

    }

    private void prepareRecipe() {

    }

    private List<Recipe> prepareRecipesList() {
        List<Recipe> recipes = new ArrayList<>();

        UnitOfMeasure eachUom = checkUnits("Each").get();
        UnitOfMeasure tableSpoonUom = checkUnits("Tablespoon").get();
        UnitOfMeasure teapoonUom = checkUnits("Teaspoon").get();
        UnitOfMeasure dashUom = checkUnits("Dash").get();
        UnitOfMeasure pintUom = checkUnits("Pint").get();
        UnitOfMeasure cupsUom = checkUnits("Cup").get();

        Category americanCategory = checkCategory("American").get();
        Category mexicanCategory = checkCategory("Mexican").get();

//        Notes guacamoleNotes = new Notes();
//        Recipe guacamoleRecipe = new Recipe();
//
//        Notes tacoNotes = new Notes();
//        Recipe tacosRecipe = new Recipe();
//
//        recipes.add(guacamoleRecipe);
//        recipes.add(tacosRecipe);

        return recipes;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        //recipeRepository.saveAll(prepareRecipesList());
    }
}
