package pl.kkowalewski.recipeapp.service;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import pl.kkowalewski.recipeapp.model.Recipe;
import pl.kkowalewski.recipeapp.repository.RecipeRepository;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class RecipeServiceImplTest {

    /*------------------------ FIELDS REGION ------------------------*/
    private RecipeService recipeService;

    @Mock
    private RecipeRepository recipeRepository;

    /*------------------------ METHODS REGION ------------------------*/
    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
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