package pl.kkowalewski.recipeapp.service.recipe;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import pl.kkowalewski.recipeapp.command.RecipeCommand;
import pl.kkowalewski.recipeapp.converter.commandto.RecipeCommandToRecipe;
import pl.kkowalewski.recipeapp.converter.tocommand.RecipeToRecipeCommand;
import pl.kkowalewski.recipeapp.model.Recipe;
import pl.kkowalewski.recipeapp.repository.RecipeRepository;
import pl.kkowalewski.recipeapp.service.recipe.RecipeService;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RecipeServiceITTest {

    /*------------------------ FIELDS REGION ------------------------*/
    private static final String NEW_DESCRIPTION = "New Description";

    @Autowired
    private RecipeService recipeService;

    @Autowired
    private RecipeRepository recipeRepository;

    @Autowired
    private RecipeCommandToRecipe recipeCommandToRecipe;

    @Autowired
    private RecipeToRecipeCommand recipeToRecipeCommand;

    /*------------------------ METHODS REGION ------------------------*/
    @Transactional
    @Test
    public void saveDescriptionTest() {
        Iterable<Recipe> recipes = recipeRepository.findAll();
        Recipe testRecice = recipes.iterator().next();
        RecipeCommand testRecipeCommand = recipeToRecipeCommand.convert(testRecice);

        testRecipeCommand.setDescription(NEW_DESCRIPTION);
        RecipeCommand savedRecipeCommand = recipeService.saveRecipeCommand(testRecipeCommand);

        assertEquals(NEW_DESCRIPTION, savedRecipeCommand.getDescription());
        assertEquals(testRecice.getId(), savedRecipeCommand.getId());
        assertEquals(testRecice.getCategories().size(), savedRecipeCommand.getCategories().size());
        assertEquals(testRecice.getIngredients().size(),
                savedRecipeCommand.getIngredients().size());
    }
}
    