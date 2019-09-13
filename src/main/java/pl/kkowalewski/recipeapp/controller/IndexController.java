package pl.kkowalewski.recipeapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.kkowalewski.recipeapp.service.recipe.RecipeService;

import static pl.kkowalewski.recipeapp.constant.AppConstants.INDEX;
import static pl.kkowalewski.recipeapp.constant.AppConstants.RECIPES;
import static pl.kkowalewski.recipeapp.constant.AppConstants.ROOT;
import static pl.kkowalewski.recipeapp.constant.AppConstants.SLASH;

@Controller
public class IndexController {

    /*------------------------ FIELDS REGION ------------------------*/
    private final RecipeService recipeService;

    /*------------------------ METHODS REGION ------------------------*/
    public IndexController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @RequestMapping({ROOT, SLASH, "/" + INDEX})
    public String getIndexPage(Model model) {
        model.addAttribute(RECIPES, recipeService.prepareRecipeSet());

        return INDEX;
    }
}
