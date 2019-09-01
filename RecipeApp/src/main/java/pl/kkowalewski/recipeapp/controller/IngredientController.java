package pl.kkowalewski.recipeapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.kkowalewski.recipeapp.command.IngredientCommand;
import pl.kkowalewski.recipeapp.command.UnitOfMeasureCommand;
import pl.kkowalewski.recipeapp.service.ingredient.IngredientService;
import pl.kkowalewski.recipeapp.service.recipe.RecipeService;
import pl.kkowalewski.recipeapp.service.unitofmeasure.UnitOfMeasureService;

@Controller
public class IngredientController {

    /*------------------------ FIELDS REGION ------------------------*/
    public static final String RECIPE = "recipe";
    public static final String INGREDIENT = "ingredient";
    public static final String INGREDIENTS = "ingredients";
    public static final String LIST = "list";
    public static final String SHOW = "show";
    public static final String UOM_LIST = "uomList";
    public static final String INGREDIENT_FORM = "ingredientform";
    public static final String UPDATE = "update";
    public static final String REDIRECT = "redirect:/";
    public static final String NEW = "new";

    public static final String LIST_INGREDIENTS = "/" + RECIPE + "/{recipeId}/" + INGREDIENTS;
    public static final String SHOW_RECIPE_INGREDIENT =
            RECIPE + "/{recipeId}/" + INGREDIENT + "/{id}/" + SHOW;
    public static final String UPDATE_RECIPE_INGREDIENT =
            RECIPE + "/{recipeId}/" + INGREDIENT + "/{id}/" + UPDATE;
    public static final String SAVE_UPDATE = RECIPE + "/{recipeId}/" + INGREDIENT;
    public static final String NEW_RECIPE = RECIPE + "/{recipeId}/" + INGREDIENT + "/" + NEW;

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

    @GetMapping
    @RequestMapping(LIST_INGREDIENTS)
    public String listIngredients(@PathVariable String recipeId, Model model) {
        model.addAttribute(RECIPE, recipeService.findCommandById(Long.valueOf(recipeId)));

        return RECIPE + "/" + INGREDIENT + "/" + LIST;
    }

    @GetMapping
    @RequestMapping(SHOW_RECIPE_INGREDIENT)
    public String showRecipeIngredient(@PathVariable String recipeId,
                                       @PathVariable String id, Model model) {
        model.addAttribute(INGREDIENT, ingredientService
                .findByRecipeIdAndIngredientId(Long.valueOf(recipeId), Long.valueOf(id)));

        return RECIPE + "/" + INGREDIENT + "/" + SHOW;
    }

    @GetMapping
    @RequestMapping(NEW_RECIPE)
    public String newRecipe(@PathVariable String recipeId, Model model) {
        IngredientCommand ingredientCommand = new IngredientCommand();
        ingredientCommand.setRecipeId(Long.valueOf(recipeId));
        model.addAttribute(INGREDIENT, ingredientCommand);

        ingredientCommand.setUom(new UnitOfMeasureCommand());
        model.addAttribute(UOM_LIST, unitOfMeasureService.listAllUoms());

        return RECIPE + "/" + INGREDIENT + "/" + INGREDIENT_FORM;
    }

    @GetMapping
    @RequestMapping(UPDATE_RECIPE_INGREDIENT)
    public String updateRecipeIngredient(@PathVariable String recipeId,
                                         @PathVariable String id, Model model) {
        model.addAttribute(INGREDIENT, ingredientService.findByRecipeIdAndIngredientId(
                Long.valueOf(recipeId), Long.valueOf(id)));

        model.addAttribute(UOM_LIST, unitOfMeasureService.listAllUoms());

        return RECIPE + "/" + INGREDIENT + "/" + INGREDIENT_FORM;
    }

    @GetMapping
    @RequestMapping(SAVE_UPDATE)
    public String saveOrUpdate(@ModelAttribute IngredientCommand ingredientCommand) {
        IngredientCommand command = ingredientService.saveIngredientCommand(ingredientCommand);

        return REDIRECT + RECIPE + "/" + command.getRecipeId() + "/"
                + INGREDIENT + "/" + command.getId() + "/" + SHOW;
    }
}
