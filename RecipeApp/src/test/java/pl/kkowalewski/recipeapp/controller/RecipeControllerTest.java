package pl.kkowalewski.recipeapp.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import pl.kkowalewski.recipeapp.model.Recipe;
import pl.kkowalewski.recipeapp.service.RecipeService;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class RecipeControllerTest {

    /*------------------------ FIELDS REGION ------------------------*/
    private static final Long RECIPE_ID = 1L;

    @Mock
    private RecipeService recipeService;
    @InjectMocks
    private RecipeController recipeController;

    /*------------------------ METHODS REGION ------------------------*/
    @Before
    public void setUp() {
        //recipeController = new RecipeController(recipeService);
    }

    private Recipe prepareRecipe() {
        return new Recipe(RECIPE_ID);
    }

    @Test
    public void showById() {

    }
}
    