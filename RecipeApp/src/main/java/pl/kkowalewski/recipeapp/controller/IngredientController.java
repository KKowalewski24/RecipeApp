package pl.kkowalewski.recipeapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.kkowalewski.recipeapp.service.RecipeService;

@Controller
public class IngredientController {

    /*------------------------ FIELDS REGION ------------------------*/
    public static final String RECIPE = "recipe";
    public static final String INGREDIENTS = "ingredients";
    public static final String LIST = "list";

    public static final String LIST_INGREDIENTS = "/" + RECIPE + "/{id}/" + INGREDIENTS;

    private final RecipeService recipeService;

    /*------------------------ METHODS REGION ------------------------*/
    public IngredientController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @GetMapping
    @RequestMapping(LIST_INGREDIENTS)
    public String listIngredients(@PathVariable String id, Model model) {
        model.addAttribute(RECIPE, recipeService.findCommandById(Long.valueOf(id)));

        return RECIPE + "/" + INGREDIENTS + "/" + LIST;
    }
}
