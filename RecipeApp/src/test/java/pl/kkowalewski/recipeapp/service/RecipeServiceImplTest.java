package pl.kkowalewski.recipeapp.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import pl.kkowalewski.recipeapp.model.Recipe;
import pl.kkowalewski.recipeapp.repository.RecipeRepository;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class RecipeServiceImplTest {

    /*------------------------ FIELDS REGION ------------------------*/
    private RecipeService recipeService;

    @Mock
    private RecipeRepository recipeRepository;

    /*------------------------ METHODS REGION ------------------------*/
    @Before
    public void setUp() {
        recipeService = new RecipeServiceImpl(recipeRepository);
    }

    @Test
    public void prepareRecipeSet() {
        Set<Recipe> recipesData = new HashSet();
        Recipe recipe = new Recipe();
        recipesData.add(recipe);
        when(recipeService.prepareRecipeSet()).thenReturn(recipesData);
        Set<Recipe> recipes = recipeService.prepareRecipeSet();

        assertEquals(recipes.size(), 1);
        verify(recipeRepository, times(1)).findAll();
    }
}