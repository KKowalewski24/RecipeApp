package pl.kkowalewski.recipeapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import pl.kkowalewski.recipeapp.command.RecipeCommand;
import pl.kkowalewski.recipeapp.service.recipe.RecipeService;

import javax.validation.Valid;

import static pl.kkowalewski.recipeapp.constant.AppConstants.RECIPE;
import static pl.kkowalewski.recipeapp.constant.AppConstants.RECIPE_DELETE;
import static pl.kkowalewski.recipeapp.constant.AppConstants.RECIPE_NEW;
import static pl.kkowalewski.recipeapp.constant.AppConstants.RECIPE_RECIPE_FORM;
import static pl.kkowalewski.recipeapp.constant.AppConstants.RECIPE_SHOW;
import static pl.kkowalewski.recipeapp.constant.AppConstants.RECIPE_UPDATE;
import static pl.kkowalewski.recipeapp.constant.AppConstants.REDIRECT;
import static pl.kkowalewski.recipeapp.constant.AppConstants.SHOW;

@Controller
public class RecipeController {

    /*------------------------ FIELDS REGION ------------------------*/

    private final RecipeService recipeService;

    /*------------------------ METHODS REGION ------------------------*/
    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @GetMapping(RECIPE_SHOW)
    public String showById(@PathVariable String id, Model model) {
        model.addAttribute(RECIPE, recipeService.findById(Long.valueOf(id)));

        return RECIPE + "/" + SHOW;
    }

    @GetMapping(RECIPE_NEW)
    public String newRecipe(Model model) {
        model.addAttribute(RECIPE, new RecipeCommand());

        return RECIPE_RECIPE_FORM;
    }

    @GetMapping(RECIPE_UPDATE)
    public String updateRecipe(@PathVariable String id, Model model) {
        model.addAttribute(RECIPE, recipeService.findCommandById(Long.valueOf(id)));

        return RECIPE_RECIPE_FORM;
    }

    @PostMapping(RECIPE)
    public String saveOrUpdate(@Valid @ModelAttribute(RECIPE) RecipeCommand recipeCommand,
                               BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return RECIPE_RECIPE_FORM;
        }

        return REDIRECT + RECIPE + "/"
                + recipeService.saveRecipeCommand(recipeCommand).getId() + "/" + SHOW;
    }

    @GetMapping(RECIPE_DELETE)
    public String deleteById(@PathVariable String id) {
        recipeService.deleteById(Long.valueOf(id));

        return REDIRECT;
    }
}
