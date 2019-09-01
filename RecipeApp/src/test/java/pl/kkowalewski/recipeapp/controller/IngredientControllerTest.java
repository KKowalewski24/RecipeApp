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
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

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

    private RecipeCommand prepareRecipeCommand() {
        return new RecipeCommand(RECIPE_ID);
    }

    private IngredientCommand prepareIngredientCommand() {
        return new IngredientCommand(INGREDIENT_ID);
    }

    private IngredientCommand prepareIngredientCommand(Long id, Long recipeId) {
        return new IngredientCommand(id, recipeId);
    }

    @Test
    public void listIngredientsTest() throws Exception {
        when(recipeService.findCommandById(anyLong())).thenReturn(prepareRecipeCommand());

        mockMvc.perform(get("/" + IngredientController.RECIPE + "/1/"
                + IngredientController.INGREDIENTS))
                .andExpect(status().isOk())
                .andExpect(view().name(IngredientController.RECIPE
                        + "/" + IngredientController.INGREDIENT
                        + "/" + IngredientController.LIST))
                .andExpect(model().attributeExists(IngredientController.RECIPE));

        verify(recipeService).findCommandById(anyLong());
    }

    @Test
    public void showRecipeIngredientTest() throws Exception {
        when(ingredientService.findByRecipeIdAndIngredientId(anyLong(), anyLong()))
                .thenReturn(prepareIngredientCommand());

        mockMvc.perform(get("/" + IngredientController.RECIPE + "/1/"
                + IngredientController.INGREDIENT + "/2/" + IngredientController.SHOW))
                .andExpect(status().isOk())
                .andExpect(view().name(IngredientController.RECIPE + "/"
                        + IngredientController.INGREDIENT + "/" + IngredientController.SHOW))
                .andExpect(model().attributeExists(IngredientController.INGREDIENT));
    }

    @Test
    public void updateRecipeIngredientTest() throws Exception {
        when(ingredientService.findByRecipeIdAndIngredientId(anyLong(), anyLong()))
                .thenReturn(prepareIngredientCommand());
        when(unitOfMeasureService.listAllUoms()).thenReturn(new HashSet<>());

        mockMvc.perform(get("/" + IngredientController.RECIPE + "/1/"
                + IngredientController.INGREDIENT + "/2/" + IngredientController.UPDATE))
                .andExpect(status().isOk())
                .andExpect(view().name(IngredientController.RECIPE + "/"
                        + IngredientController.INGREDIENT + "/" + IngredientController.INGREDIENT_FORM))
                .andExpect(model().attributeExists(IngredientController.INGREDIENT))
                .andExpect(model().attributeExists(IngredientController.UOM_LIST));
    }

    @Test
    public void saveOrUpdateTest() throws Exception {
        when(ingredientService.saveIngredientCommand(any()))
                .thenReturn(prepareIngredientCommand(ING_THREE_ID, RECIPE_ID));

        mockMvc.perform(post("/" + IngredientController.RECIPE + "/2/"
                + IngredientController.INGREDIENT)
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("id", "")
                .param("description", "some string")
        )
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name(IngredientController.REDIRECT +
                        IngredientController.RECIPE + "/1/" + IngredientController.INGREDIENT
                        + "/3/" + IngredientController.SHOW));
    }
}
    