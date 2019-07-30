package pl.kkowalewski.recipeapp.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.kkowalewski.recipeapp.service.RecipeService;

@Slf4j
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
