package pl.kkowalewski.recipeapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.kkowalewski.recipeapp.service.recipe.RecipeService;

@Controller
public class IndexController {

    /*------------------------ FIELDS REGION ------------------------*/
    public static final String ROOT = "";
    public static final String SLASH = "/";
    public static final String INDEX = "/index";
    public static final String ATTRIBUTE = "recipes";

    private final RecipeService recipeService;

    /*------------------------ METHODS REGION ------------------------*/
    public IndexController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @RequestMapping({ROOT, SLASH, INDEX})
    public String getIndexPage(Model model) {
        model.addAttribute(ATTRIBUTE, recipeService.prepareRecipeSet());

        return INDEX.substring(1);
    }
}
