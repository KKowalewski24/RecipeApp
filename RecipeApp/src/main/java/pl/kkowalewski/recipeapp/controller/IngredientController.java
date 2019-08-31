package pl.kkowalewski.recipeapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.kkowalewski.recipeapp.service.ingredient.IngredientService;
import pl.kkowalewski.recipeapp.service.recipe.RecipeService;

@Controller
public class IngredientController {

    /*------------------------ FIELDS REGION ------------------------*/
    public static final String RECIPE = "recipe";
    public static final String INGREDIENT = "ingredient";
    public static final String INGREDIENTS = "ingredients";
    public static final String LIST = "list";
    public static final String SHOW = "show";

    public static final String LIST_INGREDIENTS = "/" + RECIPE + "/{recipeId}/" + INGREDIENTS;
    public static final String SHOW_RECIPE_INGREDIENT = RECIPE + "/{recipeId}/"
            + INGREDIENT + "/{id}/" + SHOW;
    private final RecipeService recipeService;
    private final IngredientService ingredientService;

    /*------------------------ METHODS REGION ------------------------*/
    public IngredientController(RecipeService recipeService, IngredientService ingredientService) {
        this.recipeService = recipeService;
        this.ingredientService = ingredientService;
    }

    @GetMapping
    @RequestMapping(LIST_INGREDIENTS)
    public String listIngredients(@PathVariable String recipeId, Model model) {
        model.addAttribute(RECIPE, recipeService.findCommandById(Long.valueOf(recipeId)));

        return RECIPE + "/" + INGREDIENT + "/" + LIST;
    }

    @GetMapping
    @RequestMapping(SHOW_RECIPE_INGREDIENT)
    public String showRecipeIngredient(@PathVariable String recipeId,
                                       @PathVariable String id, Model model) {
        model.addAttribute(INGREDIENT, ingredientService
                .findByRecipeIdAndIngredientId(Long.valueOf(recipeId), Long.valueOf(id)));

        return RECIPE + "/" + INGREDIENT + "/" + SHOW;
    }
}
