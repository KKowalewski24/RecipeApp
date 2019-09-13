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
import pl.kkowalewski.recipeapp.command.IngredientCommand;
import pl.kkowalewski.recipeapp.command.RecipeCommand;
import pl.kkowalewski.recipeapp.service.ingredient.IngredientService;
import pl.kkowalewski.recipeapp.service.recipe.RecipeService;
import pl.kkowalewski.recipeapp.service.unitofmeasure.UnitOfMeasureService;

import java.util.HashSet;

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
import static pl.kkowalewski.recipeapp.constant.AppConstants.INGREDIENT;
import static pl.kkowalewski.recipeapp.constant.AppConstants.INGREDIENTS;
import static pl.kkowalewski.recipeapp.constant.AppConstants.INGREDIENT_FORM;
import static pl.kkowalewski.recipeapp.constant.AppConstants.LIST;
import static pl.kkowalewski.recipeapp.constant.AppConstants.NEW;
import static pl.kkowalewski.recipeapp.constant.AppConstants.RECIPE;
import static pl.kkowalewski.recipeapp.constant.AppConstants.REDIRECT;
import static pl.kkowalewski.recipeapp.constant.AppConstants.SHOW;
import static pl.kkowalewski.recipeapp.constant.AppConstants.UOM_LIST;
import static pl.kkowalewski.recipeapp.constant.AppConstants.UPDATE;

@RunWith(MockitoJUnitRunner.class)
public class IngredientControllerTest {

    /*------------------------ FIELDS REGION ------------------------*/
    private static final Long RECIPE_ID = 1L;
    private static final Long INGREDIENT_ID = 2L;
    private static final Long ING_THREE_ID = 3L;

    @Mock
    private RecipeService recipeService;

    @Mock
    private IngredientService ingredientService;

    @Mock
    private UnitOfMeasureService unitOfMeasureService;

    @InjectMocks
    private IngredientController ingredientController;

    private MockMvc mockMvc;

    /*------------------------ METHODS REGION ------------------------*/
    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(ingredientController).build();
    }

    private RecipeCommand prepareRecipeCommand(Long id) {
        return new RecipeCommand(id);
    }

    private IngredientCommand prepareIngredientCommand(Long id) {
        return new IngredientCommand(id);
    }

    private IngredientCommand prepareIngredientCommand(Long id, Long recipeId) {
        return new IngredientCommand(id, recipeId);
    }

    @Test
    public void listIngredientsTest() throws Exception {
        when(recipeService.findCommandById(anyLong())).thenReturn(prepareRecipeCommand(RECIPE_ID));

        mockMvc.perform(get("/" + RECIPE + "/1/" + INGREDIENTS))
                .andExpect(status().isOk())
                .andExpect(view().name(RECIPE + "/" + INGREDIENT + "/" + LIST))
                .andExpect(model().attributeExists(RECIPE));

        verify(recipeService).findCommandById(anyLong());
    }

    @Test
    public void showRecipeIngredientTest() throws Exception {
        when(ingredientService.findByRecipeIdAndIngredientId(anyLong(), anyLong()))
                .thenReturn(prepareIngredientCommand(INGREDIENT_ID));

        mockMvc.perform(get("/" + RECIPE + "/1/" + INGREDIENT + "/2/" + SHOW))
                .andExpect(status().isOk())
                .andExpect(view().name(RECIPE + "/" + INGREDIENT + "/" + SHOW))
                .andExpect(model().attributeExists(INGREDIENT));
    }

    @Test
    public void newRecipeTest() throws Exception {
        lenient().when(recipeService.findCommandById(anyLong()))
                .thenReturn(prepareRecipeCommand(RECIPE_ID));
        when(unitOfMeasureService.listAllUoms()).thenReturn(new HashSet<>());

        mockMvc.perform(get("/" + RECIPE + "/1/" + INGREDIENT + "/" + NEW))
                .andExpect(status().isOk())
                .andExpect(view().name(RECIPE + "/" + INGREDIENT
                        + "/" + INGREDIENT_FORM))
                .andExpect(model().attributeExists(INGREDIENT))
                .andExpect(model().attributeExists(UOM_LIST));

    }

    @Test
    public void updateRecipeIngredientTest() throws Exception {
        when(ingredientService.findByRecipeIdAndIngredientId(anyLong(), anyLong()))
                .thenReturn(prepareIngredientCommand(INGREDIENT_ID));
        when(unitOfMeasureService.listAllUoms()).thenReturn(new HashSet<>());

        mockMvc.perform(get("/" + RECIPE + "/1/" + INGREDIENT + "/2/" + UPDATE))
                .andExpect(status().isOk())
                .andExpect(view().name(RECIPE + "/" + INGREDIENT
                        + "/" + INGREDIENT_FORM))
                .andExpect(model().attributeExists(INGREDIENT))
                .andExpect(model().attributeExists(UOM_LIST));
    }

    @Test
    public void saveOrUpdateTest() throws Exception {
        when(ingredientService.saveIngredientCommand(any()))
                .thenReturn(prepareIngredientCommand(ING_THREE_ID, RECIPE_ID));

        mockMvc.perform(post("/" + RECIPE + "/2/" + INGREDIENT)
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("id", "")
                .param("description", "some string")
        )
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name(REDIRECT + RECIPE + "/1/"
                        + INGREDIENT + "/3/" + SHOW));
    }

    @Test
    public void deleteIngredientTest() throws Exception {
        mockMvc.perform(get("/" + RECIPE + "/2/" + INGREDIENT + "/3/" + DELETE))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name(REDIRECT + RECIPE + "/2/" + INGREDIENTS));

        verify(ingredientService).deleteById(anyLong(), anyLong());
    }
}
    