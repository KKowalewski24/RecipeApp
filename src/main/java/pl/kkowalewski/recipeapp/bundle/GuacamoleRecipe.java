package pl.kkowalewski.recipeapp.bundle;

import java.util.ListResourceBundle;

public class GuacamoleRecipe extends ListResourceBundle {

    /*------------------------ FIELDS REGION ------------------------*/

    /*------------------------ METHODS REGION ------------------------*/
    @Override
    protected Object[][] getContents() {
        return new Object[][]{
                {"_description", "Perfect Guacamole"},
                {"_prepTime", 10},
                {"_cookTime", 0},
                {"_directions", "1 Cut avocado, remove flesh: Cut the avocados in half. Remove "
                        + "seed. Score the inside of the avocado with a blunt knife and scoop out "
                        + "the flesh with a spoon\n2 Mash with a fork: Using a fork, roughly mash "
                        + "the avocado. (Don't overdo it! The guacamole should be a little chunky.)"
                        + "\n3 Add salt, lime juice, and the rest: Sprinkle with salt and lime (or "
                        + "lemon) juice. The acid in the lime juice will provide some balance to "
                        + "the richness of the avocado and will help delay the avocados from "
                        + "turning brown.\nAdd the chopped onion, cilantro, black pepper, and "
                        + "chiles. Chili peppers vary individually in their hotness. So, start with"
                        + " a half of one chili pepper and add to the guacamole to your desired "
                        + "degree of hotness.\nRemember that much of this is done to taste because "
                        + "of the variability in the fresh ingredients. Start with this recipe and "
                        + "adjust to your taste.\n4 Cover with plastic and chill to store: Place "
                        + "plastic wrap on the surface of the guacamole cover it and to prevent air"
                        + " reaching it. (The oxygen in the air causes oxidation which will turn "
                        + "the guacamole brown.) Refrigerate until ready to serve.\nChilling "
                        + "tomatoes hurts their flavor, so if you want to add chopped tomato to "
                        + "your guacamole, add it just before serving.\n\n\nRead more: http://www"
                        + ".simplyrecipes.com/recipes/perfect_guacamole/#ixzz4jvpiV9Sd"},
        };
    }
}
