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
    public static final String RECIPE = "recipe";
    public static final String RECIPE_FORM = "recipeform";
    public static final String SHOW = "show";
    public static final String NEW = "new";
    public static final String UPDATE = "update";
    public static final String REDIRECT = "redirect:/";

    public static final String RECIPE_SHOW = "/" + RECIPE + "/{id}/" + SHOW;
    public static final String RECIPE_NEW = RECIPE + "/" + NEW;
    public static final String RECIPE_UPDATE = RECIPE + "/{id}/" + UPDATE;
    public static final String RECIPE_RECIPE_FORM = RECIPE + "/" + RECIPE_FORM;

    private final RecipeService recipeService;

    /*------------------------ METHODS REGION ------------------------*/
    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @RequestMapping(RECIPE_SHOW)
    public String showById(@PathVariable String id, Model model) {
        model.addAttribute(RECIPE, recipeService.findById(Long.valueOf(id)));

        return RECIPE + "/" + SHOW;
    }

    @RequestMapping(RECIPE_NEW)
    public String newRecipe(Model model) {
        model.addAttribute(RECIPE, new RecipeCommand());

        return RECIPE_RECIPE_FORM;
    }

    @RequestMapping(RECIPE_UPDATE)
    public String updateRecipe(@PathVariable String id, Model model) {
        model.addAttribute(RECIPE, recipeService.findCommandById(Long.valueOf(id)));

        return RECIPE_RECIPE_FORM;
    }

    @PostMapping
    @RequestMapping(RECIPE)
    public String saveOrUpdate(@ModelAttribute RecipeCommand recipeCommand) {
        return REDIRECT + RECIPE + "/"
                + recipeService.saveRecipeCommand(recipeCommand).getId() + "/" + SHOW;
    }
}
