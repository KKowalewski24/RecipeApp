package pl.kkowalewski.recipeapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.kkowalewski.recipeapp.command.RecipeCommand;
import pl.kkowalewski.recipeapp.service.RecipeService;

@Controller
public class RecipeController {

    /*------------------------ FIELDS REGION ------------------------*/
    public static final String RECIPE_SHOW = "/recipe/show";
    public static final String RECIPE_NEW = "recipe/new";
    public static final String RECIPE_RECIPE_FORM = "recipe/recipeform";
    public static final String ATTRIBUTE = "recipe";

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

    @RequestMapping(RECIPE_NEW)
    public String newRecipe(Model model) {
        model.addAttribute(ATTRIBUTE, new RecipeCommand());

        return RECIPE_RECIPE_FORM;
    }

    @PostMapping
    @RequestMapping(ATTRIBUTE)
    public String saveOrUpdate(@ModelAttribute RecipeCommand recipeCommand) {
        return "redirect:" + RECIPE_SHOW + "/" + recipeService.saveRecipeCommand(recipeCommand).getId();
    }
}
