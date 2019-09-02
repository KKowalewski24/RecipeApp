package pl.kkowalewski.recipeapp.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import pl.kkowalewski.recipeapp.command.RecipeCommand;
import pl.kkowalewski.recipeapp.model.Recipe;
import pl.kkowalewski.recipeapp.service.recipe.RecipeService;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@RunWith(MockitoJUnitRunner.class)
public class RecipeControllerTest {

    /*------------------------ FIELDS REGION ------------------------*/
    private static final Long RECIPE_ID = 1L;

    @Mock
    private RecipeService recipeService;

    @InjectMocks
    private RecipeController recipeController;

    private MockMvc mockMvc;

    /*------------------------ METHODS REGION ------------------------*/
    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(recipeController).build();
    }

    private Recipe prepareRecipe(Long id) {
        return new Recipe(id);
    }

    private RecipeCommand prepareRecipeCommand(Long id) {
        return new RecipeCommand(id);
    }

    @Test
    public void showById() throws Exception {
        when(recipeService.findById(anyLong())).thenReturn(prepareRecipe(RECIPE_ID));

        mockMvc.perform(get("/" + RecipeController.RECIPE + "/1/" + RecipeController.SHOW))
                .andExpect(status().isOk())
                .andExpect(view().name(RecipeController.RECIPE + "/" + RecipeController.SHOW))
                .andExpect(model().attributeExists(RecipeController.RECIPE));
    }

    @Test
    public void newRecipeTest() throws Exception {
        mockMvc.perform(get("/" + RecipeController.RECIPE_NEW))
                .andExpect(status().isOk())
                .andExpect(view().name(RecipeController.RECIPE_RECIPE_FORM))
                .andExpect(model().attributeExists(RecipeController.RECIPE));
    }

    @Test
    public void updateRecipeTest() throws Exception {
        when(recipeService.saveRecipeCommand(any())).thenReturn(prepareRecipeCommand(RECIPE_ID));

        mockMvc.perform(post("/" + RecipeController.RECIPE)
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("id", "")
                .param("description", "some string"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name(RecipeController.REDIRECT
                        + RecipeController.RECIPE + "/1/" + RecipeController.SHOW));
    }

    @Test
    public void saveOrUpdateTest() throws Exception {
        when(recipeService.findCommandById(anyLong())).thenReturn(prepareRecipeCommand(RECIPE_ID));

        mockMvc.perform(get("/" + RecipeController.RECIPE + "/1/" + RecipeController.UPDATE))
                .andExpect(status().isOk())
                .andExpect(view().name(RecipeController.RECIPE_RECIPE_FORM))
                .andExpect(model().attributeExists(RecipeController.RECIPE));
    }

    @Test
    public void deleteByIdTest() throws Exception {
        mockMvc.perform(get("/" + RecipeController.RECIPE + "/1/" + RecipeController.DELETE))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name(RecipeController.REDIRECT));

        verify(recipeService).deleteById(anyLong());
    }
}
    