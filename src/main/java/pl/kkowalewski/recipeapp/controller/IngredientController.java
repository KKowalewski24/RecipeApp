package pl.kkowalewski.recipeapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import pl.kkowalewski.recipeapp.command.IngredientCommand;
import pl.kkowalewski.recipeapp.command.UnitOfMeasureCommand;
import pl.kkowalewski.recipeapp.service.ingredient.IngredientService;
import pl.kkowalewski.recipeapp.service.recipe.RecipeService;
import pl.kkowalewski.recipeapp.service.unitofmeasure.UnitOfMeasureService;

import static pl.kkowalewski.recipeapp.constant.AppConstants.INGREDIENT;
import static pl.kkowalewski.recipeapp.constant.AppConstants.INGREDIENTS;
import static pl.kkowalewski.recipeapp.constant.AppConstants.INGREDIENT_DELETE_INGREDIENT;
import static pl.kkowalewski.recipeapp.constant.AppConstants.INGREDIENT_FORM;
import static pl.kkowalewski.recipeapp.constant.AppConstants.INGREDIENT_LIST_INGREDIENTS;
import static pl.kkowalewski.recipeapp.constant.AppConstants.INGREDIENT_NEW_RECIPE;
import static pl.kkowalewski.recipeapp.constant.AppConstants.INGREDIENT_SAVE_UPDATE;
import static pl.kkowalewski.recipeapp.constant.AppConstants.INGREDIENT_SHOW_RECIPE_INGREDIENT;
import static pl.kkowalewski.recipeapp.constant.AppConstants.INGREDIENT_UPDATE_RECIPE_INGREDIENT;
import static pl.kkowalewski.recipeapp.constant.AppConstants.LIST;
import static pl.kkowalewski.recipeapp.constant.AppConstants.RECIPE;
import static pl.kkowalewski.recipeapp.constant.AppConstants.REDIRECT;
import static pl.kkowalewski.recipeapp.constant.AppConstants.SHOW;
import static pl.kkowalewski.recipeapp.constant.AppConstants.UOM_LIST;

@Controller
public class IngredientController {

    /*------------------------ FIELDS REGION ------------------------*/

    private final RecipeService recipeService;
    private final IngredientService ingredientService;
    private final UnitOfMeasureService unitOfMeasureService;

    /*------------------------ METHODS REGION ------------------------*/
    public IngredientController(RecipeService recipeService, IngredientService ingredientService,
                                UnitOfMeasureService unitOfMeasureService) {
        this.recipeService = recipeService;
        this.ingredientService = ingredientService;
        this.unitOfMeasureService = unitOfMeasureService;
    }

    @GetMapping(INGREDIENT_LIST_INGREDIENTS)
    public String listIngredients(@PathVariable String recipeId, Model model) {
        model.addAttribute(RECIPE, recipeService.findCommandById(Long.valueOf(recipeId)));

        return RECIPE + "/" + INGREDIENT + "/" + LIST;
    }

    @GetMapping(INGREDIENT_SHOW_RECIPE_INGREDIENT)
    public String showRecipeIngredient(@PathVariable String recipeId,
                                       @PathVariable String id, Model model) {
        model.addAttribute(INGREDIENT, ingredientService
                .findByRecipeIdAndIngredientId(Long.valueOf(recipeId), Long.valueOf(id)));

        return RECIPE + "/" + INGREDIENT + "/" + SHOW;
    }

    @GetMapping(INGREDIENT_NEW_RECIPE)
    public String newRecipe(@PathVariable String recipeId, Model model) {
        IngredientCommand ingredientCommand = new IngredientCommand();
        ingredientCommand.setRecipeId(Long.valueOf(recipeId));
        model.addAttribute(INGREDIENT, ingredientCommand);

        ingredientCommand.setUom(new UnitOfMeasureCommand());
        model.addAttribute(UOM_LIST, unitOfMeasureService.listAllUoms());

        return RECIPE + "/" + INGREDIENT + "/" + INGREDIENT_FORM;
    }

    @GetMapping(INGREDIENT_UPDATE_RECIPE_INGREDIENT)
    public String updateRecipeIngredient(@PathVariable String recipeId,
                                         @PathVariable String id, Model model) {
        model.addAttribute(INGREDIENT, ingredientService.findByRecipeIdAndIngredientId(
                Long.valueOf(recipeId), Long.valueOf(id)));

        model.addAttribute(UOM_LIST, unitOfMeasureService.listAllUoms());

        return RECIPE + "/" + INGREDIENT + "/" + INGREDIENT_FORM;
    }

    @PostMapping(INGREDIENT_SAVE_UPDATE)
    public String saveOrUpdate(@ModelAttribute IngredientCommand ingredientCommand) {
        IngredientCommand command = ingredientService.saveIngredientCommand(ingredientCommand);

        return REDIRECT + RECIPE + "/" + command.getRecipeId() + "/"
                + INGREDIENT + "/" + command.getId() + "/" + SHOW;
    }

    @GetMapping(INGREDIENT_DELETE_INGREDIENT)
    public String deleteIngredient(@PathVariable String recipeId, @PathVariable String id) {
        ingredientService.deleteById(Long.valueOf(recipeId), Long.valueOf(id));

        return REDIRECT + RECIPE + "/" + recipeId + "/" + INGREDIENTS;
    }
}
