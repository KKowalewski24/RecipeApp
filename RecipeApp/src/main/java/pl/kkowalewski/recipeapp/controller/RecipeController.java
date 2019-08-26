package pl.kkowalewski.recipeapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.kkowalewski.recipeapp.service.RecipeService;

@Controller
public class RecipeController {

    /*------------------------ FIELDS REGION ------------------------*/
    private static final String RECIPE_SHOW = "/recipe/show";
    private static final String ATTRIBUTE = "recipe";

    private final RecipeService recipeService;

    /*------------------------ METHODS REGION ------------------------*/
    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @RequestMapping(RECIPE_SHOW + "/{id}")
    public String showById(@PathVariable String id, Model model) {
        model.addAttribute(ATTRIBUTE, recipeService.findById(Long.valueOf(id)));

        return RECIPE_SHOW.substring(1);
    }
}
