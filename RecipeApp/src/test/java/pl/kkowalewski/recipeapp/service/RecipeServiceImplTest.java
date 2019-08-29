package pl.kkowalewski.recipeapp.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import pl.kkowalewski.recipeapp.command.RecipeCommand;
import pl.kkowalewski.recipeapp.converter.commandto.RecipeCommandToRecipe;
import pl.kkowalewski.recipeapp.converter.tocommand.RecipeToRecipeCommand;
import pl.kkowalewski.recipeapp.model.Recipe;
import pl.kkowalewski.recipeapp.repository.RecipeRepository;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class RecipeServiceImplTest {

    /*------------------------ FIELDS REGION ------------------------*/
    private static final Long RECIPE_ID = 1L;

    private RecipeService recipeService;

    @Mock
    private RecipeRepository recipeRepository;

    @Mock
    private RecipeToRecipeCommand recipeToRecipeCommand;

    @Mock
    private RecipeCommandToRecipe recipeCommandToRecipe;

    /*------------------------ METHODS REGION ------------------------*/
    @Before
    public void setUp() {
        recipeService = new RecipeServiceImpl(recipeRepository,
                recipeCommandToRecipe, recipeToRecipeCommand);
    }

    private Recipe prepareRecipe() {
        return new Recipe(RECIPE_ID);
    }

    private RecipeCommand prepareRecipeCommand() {
        return new RecipeCommand(RECIPE_ID);
    }

    @Test
    public void prepareRecipeSet() {
        Set<Recipe> recipesData = new HashSet<>(Arrays.asList(prepareRecipe()));
        when(recipeService.prepareRecipeSet()).thenReturn(recipesData);
        Set<Recipe> recipes = recipeService.prepareRecipeSet();

        assertEquals(recipes.size(), 1);
        verify(recipeRepository, times(1)).findAll();
    }

    @Test
    public void findByIdTest() {
        Optional<Recipe> recipeOptional = Optional.of(prepareRecipe());
        when(recipeRepository.findById(anyLong())).thenReturn(recipeOptional);
        Recipe recipe = recipeService.findById(RECIPE_ID);

        assertNotNull(recipe);
        verify(recipeRepository).findById(anyLong());
        verify(recipeRepository, never()).findAll();
    }

    @Test
    public void findCommandByIdTest() {
        Optional<Recipe> recipeOptional = Optional.of(prepareRecipe());
        when(recipeRepository.findById(anyLong())).thenReturn(recipeOptional);
        when(recipeToRecipeCommand.convert(any())).thenReturn(prepareRecipeCommand());

        RecipeCommand recipeCommand = recipeService.findCommandById(RECIPE_ID);

        assertNotNull(recipeCommand);
        verify(recipeRepository).findById(anyLong());
        verify(recipeRepository, never()).findAll();
    }
}
