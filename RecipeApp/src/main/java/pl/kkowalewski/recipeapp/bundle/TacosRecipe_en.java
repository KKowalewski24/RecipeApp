package pl.kkowalewski.recipeapp.bundle;

import pl.kkowalewski.recipeapp.model.Difficulty;

import java.util.ListResourceBundle;

public class TacosRecipe_en extends ListResourceBundle {

    /*------------------------ FIELDS REGION ------------------------*/

    /*------------------------ METHODS REGION ------------------------*/
    @Override
    protected Object[][] getContents() {
        return new Object[][]{
                {"_description", "Perfect Guacamole"},
                {"_prepTime", 10},
                {"_cookTime", 0},
                {"_difficulty", Difficulty.EASY},
                {"_directions", ""}
        };
    }
}
