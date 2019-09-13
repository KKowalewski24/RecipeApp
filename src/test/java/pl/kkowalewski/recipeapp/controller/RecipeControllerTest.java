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
import pl.kkowalewski.recipeapp.exception.RecipeNotFoundException;
import pl.kkowalewski.recipeapp.model.Recipe;
import pl.kkowalewski.recipeapp.service.recipe.RecipeService;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static pl.kkowalewski.recipeapp.constant.AppConstants.DELETE;
import static pl.kkowalewski.recipeapp.constant.AppConstants.ERROR_400;
import static pl.kkowalewski.recipeapp.constant.AppConstants.ERROR_404;
import static pl.kkowalewski.recipeapp.constant.AppConstants.EXCEPTION;
import static pl.kkowalewski.recipeapp.constant.AppConstants.RECIPE;
import static pl.kkowalewski.recipeapp.constant.AppConstants.RECIPE_NEW;
import static pl.kkowalewski.recipeapp.constant.AppConstants.RECIPE_RECIPE_FORM;
import static pl.kkowalewski.recipeapp.constant.AppConstants.REDIRECT;
import static pl.kkowalewski.recipeapp.constant.AppConstants.SHOW;
import static pl.kkowalewski.recipeapp.constant.AppConstants.UPDATE;

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
        mockMvc = MockMvcBuilders.standaloneSetup(recipeController)
                .setControllerAdvice(new ControllerExceptionHandler()).build();
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

        mockMvc.perform(get("/" + RECIPE + "/1/" + SHOW))
                .andExpect(status().isOk())
                .andExpect(view().name(RECIPE + "/" + SHOW))
                .andExpect(model().attributeExists(RECIPE));
    }

    @Test
    public void showByIdNotFoundExceptionTest() throws Exception {
        when(recipeService.findById(anyLong())).thenThrow(RecipeNotFoundException.class);

        mockMvc.perform(get("/" + RECIPE + "/1/" + SHOW))
                .andExpect(status().isNotFound())
                .andExpect(view().name(ERROR_404))
                .andExpect(model().attributeExists(EXCEPTION));
    }

    @Test
    public void showByIdNumberFormatExceptionTest() throws Exception {
        mockMvc.perform(get("/" + RECIPE + "/TEXT/" + SHOW))
                .andExpect(status().isBadRequest())
                .andExpect(view().name(ERROR_400))
                .andExpect(model().attributeExists(EXCEPTION));

    }

    @Test
    public void newRecipeTest() throws Exception {
        mockMvc.perform(get("/" + RECIPE_NEW))
                .andExpect(status().isOk())
                .andExpect(view().name(RECIPE_RECIPE_FORM))
                .andExpect(model().attributeExists(RECIPE));
    }

    @Test
    public void updateRecipeTest() throws Exception {
        when(recipeService.saveRecipeCommand(any()))
                .thenReturn(prepareRecipeCommand(RECIPE_ID));

        mockMvc.perform(post("/" + RECIPE)
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("id", "")
                .param("description", "some string")
                .param("directions", "some directions")
        )
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name(REDIRECT + RECIPE + "/1/" + SHOW));
    }

    @Test
    public void UpdateErrorTest() throws Exception {
        lenient().when(recipeService.saveRecipeCommand(any()))
                .thenReturn(prepareRecipeCommand(RECIPE_ID));

        mockMvc.perform(post("/" + RECIPE)
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("id", "")
                .param("cookTime", "3000")
        )
                .andExpect(status().isOk())
                .andExpect(model().attributeExists(RECIPE))
                .andExpect(view().name(RECIPE_RECIPE_FORM));
    }

    @Test
    public void saveOrUpdateTest() throws Exception {
        when(recipeService.findCommandById(anyLong()))
                .thenReturn(prepareRecipeCommand(RECIPE_ID));

        mockMvc.perform(get("/" + RECIPE + "/1/" + UPDATE))
                .andExpect(status().isOk())
                .andExpect(view().name(RECIPE_RECIPE_FORM))
                .andExpect(model().attributeExists(RECIPE));
    }

    @Test
    public void deleteByIdTest() throws Exception {
        mockMvc.perform(get("/" + RECIPE + "/1/" + DELETE))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name(REDIRECT));

        verify(recipeService).deleteById(anyLong());
    }
}
    