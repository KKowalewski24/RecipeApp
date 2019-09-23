package pl.kkowalewski.recipeapp.constant;

public class AppConstants {

    /*------------------------ FIELDS REGION ------------------------*/
    public static final String PAGE_NAME = "Tasty Recipes";
    public static final String AUTHOR = "Kamil Kowalewski";
    public static final String COPYRIGHT = "All rights reserved";
    public static final String EMAIL = "contact@tastyrecipes.org";
    public static final String TELEPHONE = "798 564 357";
    public static final String DESCRIPTION = "Website has been created to give people possibility" +
            " to share awesome recipe and improve quality of every day food. I hope it's gonna " +
            "improve people's health and their entire life.";

    public static final String ERROR_400 = "400error";
    public static final String ERROR_404 = "404error";
    public static final String EXCEPTION = "exception";
    public static final String ROOT = "";
    public static final String SLASH = "/";
    public static final String INDEX = "index";
    public static final String RECIPES = "recipes";
    public static final String RECIPE = "recipe";
    public static final String REDIRECT = "redirect:/";
    public static final String SHOW = "show";
    public static final String IMAGE = "image";
    public static final String IMAGE_UPLOAD_FORM = "imageuploadform";
    public static final String IMAGE_FILE = "imagefile";
    public static final String RECIPE_IMAGE = RECIPE + IMAGE;
    public static final String INGREDIENT = "ingredient";
    public static final String INGREDIENTS = "ingredients";
    public static final String LIST = "list";
    public static final String UOM_LIST = "uomList";
    public static final String INGREDIENT_FORM = "ingredientform";
    public static final String UPDATE = "update";
    public static final String NEW = "new";
    public static final String DELETE = "delete";
    public static final String RECIPE_FORM = "recipeform";
    public static final String ID = "/{id}/";
    public static final String RECIPE_ID = "/{recipeId}/";
    public static final String ABOUT = "about";
    public static final String CONTACT = "contact";

    public static final String PATH_ABOUT = SLASH + ABOUT;
    public static final String PATH_CONTACT = SLASH + CONTACT;

    public static final String IMAGE_SHOW_UPLOAD_FORM = RECIPE + ID + IMAGE;
    public static final String IMAGE_HANDLE_IMAGE_POST = RECIPE + ID + IMAGE;
    public static final String IMAGE_RENDER_IMAGE_DB = RECIPE + ID + RECIPE_IMAGE;

    public static final String RECIPE_SHOW = SLASH + RECIPE + ID + SHOW;
    public static final String RECIPE_NEW = RECIPE + SLASH + NEW;
    public static final String RECIPE_UPDATE = RECIPE + ID + UPDATE;
    public static final String RECIPE_DELETE = RECIPE + ID + DELETE;
    public static final String RECIPE_RECIPE_FORM = RECIPE + SLASH + RECIPE_FORM;

    public static final String INGREDIENT_LIST_INGREDIENTS = SLASH + RECIPE
            + RECIPE_ID + INGREDIENTS;
    public static final String INGREDIENT_SHOW_RECIPE_INGREDIENT = RECIPE
            + RECIPE_ID + INGREDIENT + ID + SHOW;
    public static final String INGREDIENT_UPDATE_RECIPE_INGREDIENT = RECIPE
            + RECIPE_ID + INGREDIENT + ID + UPDATE;
    public static final String INGREDIENT_SAVE_UPDATE = RECIPE + RECIPE_ID + INGREDIENT;
    public static final String INGREDIENT_NEW_RECIPE = RECIPE + RECIPE_ID
            + INGREDIENT + SLASH + NEW;
    public static final String INGREDIENT_DELETE_INGREDIENT = RECIPE + RECIPE_ID
            + INGREDIENT + ID + DELETE;

    /*------------------------ METHODS REGION ------------------------*/

}
