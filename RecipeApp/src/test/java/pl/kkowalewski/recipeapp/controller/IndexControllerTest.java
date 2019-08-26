package pl.kkowalewski.recipeapp.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;
import pl.kkowalewski.recipeapp.model.Recipe;
import pl.kkowalewski.recipeapp.service.RecipeService;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@RunWith(MockitoJUnitRunner.class)
public class IndexControllerTest {

    /*------------------------ FIELDS REGION ------------------------*/
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

    private Set<Recipe> prepareSet() {
        return new HashSet<>(Arrays.asList(
                new Recipe(1L),
                new Recipe(2L)
        ));
    }

    @Test
    public void mockMvcTest() throws Exception {
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(indexController).build();
        mockMvc.perform(get(IndexController.SLASH))
                .andExpect(status().isOk())
                .andExpect(view().name(IndexController.INDEX.substring(1)));
    }

    @Test
    public void getIndexPage() {
        Set<Recipe> recipes = prepareSet();
        when(recipeService.prepareRecipeSet()).thenReturn(recipes);

        ArgumentCaptor<Set<Recipe>> argumentCaptor = ArgumentCaptor.forClass(Set.class);

        assertEquals(indexController.getIndexPage(model), IndexController.INDEX.substring(1));

        verify(recipeService, times(1)).prepareRecipeSet();
        verify(model, times(1))
                .addAttribute(eq(IndexController.ATTRIBUTE), argumentCaptor.capture());

        Set<Recipe> controllerSet = argumentCaptor.getValue();
        assertEquals(controllerSet.size(), 2);
    }
}
