package pl.kkowalewski.recipeapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.kkowalewski.recipeapp.service.RecipeService;

@Controller
public class IndexController {

    /*------------------------ FIELDS REGION ------------------------*/
    public static final String DESCRIPTION = "index";

    private final RecipeService recipeService;

    /*------------------------ METHODS REGION ------------------------*/
    public IndexController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @RequestMapping({"", "/", "/index"})
    public String getIndexPage(Model model) {
        model.addAttribute("recipes", recipeService.prepareRecipeSet());

        return DESCRIPTION;
    }
}
