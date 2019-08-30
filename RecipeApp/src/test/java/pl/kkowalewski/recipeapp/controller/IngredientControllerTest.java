package pl.kkowalewski.recipeapp.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import pl.kkowalewski.recipeapp.command.RecipeCommand;
import pl.kkowalewski.recipeapp.service.RecipeService;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@RunWith(MockitoJUnitRunner.class)
public class IngredientControllerTest {

    /*------------------------ FIELDS REGION ------------------------*/
    private static final Long RECIPE_ID = 1L;

    @Mock
    private RecipeService recipeService;

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

    @Test
    public void listIngredientsTest() throws Exception {
        when(recipeService.findCommandById(anyLong())).thenReturn(prepareRecipeCommand());

        mockMvc.perform(get("/" + IngredientController.RECIPE + "/1/" + IngredientController.INGREDIENTS))
                .andExpect(status().isOk())
                .andExpect(view().name(IngredientController.RECIPE
                        + "/" + IngredientController.INGREDIENT
                        + "/" + IngredientController.LIST))
                .andExpect(model().attributeExists(IngredientController.RECIPE));

        verify(recipeService).findCommandById(anyLong());
    }
}
    