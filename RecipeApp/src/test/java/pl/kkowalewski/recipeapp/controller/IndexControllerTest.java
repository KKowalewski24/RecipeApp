package pl.kkowalewski.recipeapp.controller;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;
import pl.kkowalewski.recipeapp.model.Recipe;
import pl.kkowalewski.recipeapp.service.RecipeService;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

public class IndexControllerTest {

    /*------------------------ FIELDS REGION ------------------------*/
    private IndexController indexController;

    @Mock
    private RecipeService recipeService;

    @Mock
    private Model model;

    /*------------------------ METHODS REGION ------------------------*/
    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        indexController = new IndexController(recipeService);
    }

    @Test
    public void getIndexPage() {
        Set<Recipe> recipes = new HashSet<>();
        recipes.add(new Recipe(1L));
        recipes.add(new Recipe(2L));
        when(recipeService.prepareRecipeSet()).thenReturn(recipes);

        ArgumentCaptor<Set<Recipe>> argumentCaptor = ArgumentCaptor.forClass(Set.class);

        assertEquals(indexController.getIndexPage(model), IndexController.DESCRIPTION);

        verify(recipeService, times(1)).prepareRecipeSet();
        verify(model, times(1))
                .addAttribute(eq(IndexController.ATTRIBUTE), argumentCaptor.capture());

        Set<Recipe> controllerSet = argumentCaptor.getValue();
        assertEquals(controllerSet.size(), 2);
    }
}
