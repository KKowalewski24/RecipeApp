package pl.kkowalewski.recipeapp.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;
import pl.kkowalewski.recipeapp.model.Recipe;
import pl.kkowalewski.recipeapp.service.recipe.RecipeService;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@RunWith(MockitoJUnitRunner.class)
public class IndexControllerTest {

    /*------------------------ FIELDS REGION ------------------------*/
    private static final Long RECIPE_ONE_ID = 1L;
    private static final Long RECIPE_TWO_ID = 2L;

    @Mock
    private RecipeService recipeService;

    @Mock
    private Model model;

    private IndexController indexController;

    /*------------------------ METHODS REGION ------------------------*/
    @Before
    public void setUp() {
        indexController = new IndexController(recipeService);
    }

    private Set<Recipe> prepareRecipeSet(Long... longs) {
        Set<Recipe> recipes = new HashSet<>();

        for (Long it : longs) {
            recipes.add(new Recipe(it));
        }

        return recipes;
    }

    @Test
    public void mockMvcTest() throws Exception {
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(indexController).build();
        mockMvc.perform(get(IndexController.SLASH))
                .andExpect(status().isOk())
                .andExpect(view().name(IndexController.INDEX));
    }

    @Test
    public void getIndexPage() {
        Set<Recipe> recipes = prepareRecipeSet(RECIPE_ONE_ID, RECIPE_TWO_ID);
        ArgumentCaptor<Set<Recipe>> argumentCaptor = ArgumentCaptor.forClass(Set.class);

        when(recipeService.prepareRecipeSet()).thenReturn(recipes);
        assertEquals(indexController.getIndexPage(model), IndexController.INDEX);

        verify(recipeService).prepareRecipeSet();
        verify(model).addAttribute(eq(IndexController.RECIPES), argumentCaptor.capture());

        assertEquals(argumentCaptor.getValue().size(), 2);
    }
}
