package pl.kkowalewski.recipeapp.bundle;

import java.util.ListResourceBundle;

public class TacosRecipe extends ListResourceBundle {

    /*------------------------ FIELDS REGION ------------------------*/

    /*------------------------ METHODS REGION ------------------------*/
    @Override
    protected Object[][] getContents() {
        return new Object[][]{
                {"_description", "Spicy Grilled Chicken Taco"},
                {"_prepTime", 9},
                {"_cookTime", 20},
                {"_directions", "1 Prepare a gas or charcoal grill for medium-high, direct heat" +
                        ".\n" + "2 Make the marinade and coat the chicken: In a large bowl, stir " +
                        "together the chili powder, oregano, cumin, sugar, salt, garlic and " +
                        "orange zest. Stir in the orange juice and olive oil to make a loose " +
                        "paste. Add the chicken to the bowl and toss to coat all over.\n" + "Set " +
                        "aside to marinate while the grill heats and you prepare the rest of the " +
                        "toppings.\n" + "\n" + "\n" + "3 Grill the chicken: Grill the chicken for" +
                        " 3 to 4 minutes per side, or until a thermometer inserted into the " +
                        "thickest part of the meat registers 165F. Transfer to a plate and rest " +
                        "for 5 minutes.\n" + "4 Warm the tortillas: Place each tortilla on the " +
                        "grill or on a hot, dry skillet over medium-high heat. As soon as you see" +
                        " pockets of the air start to puff up in the tortilla, turn it with tongs" +
                        " and heat for a few seconds on the other side.\n" + "Wrap warmed " +
                        "tortillas in a tea towel to keep them warm until serving.\n" + "5 " +
                        "Assemble the tacos: Slice the chicken into strips. On each tortilla, " +
                        "place a small handful of arugula. Top with chicken slices, sliced " +
                        "avocado, radishes, tomatoes, and onion slices. Drizzle with the thinned " +
                        "sour cream. Serve with lime wedges.\n" + "\n" + "\n" + "Read more: " +
                        "http://www.simplyrecipes.com/recipes/spicy_grilled_chicken_tacos" +
                        "/#ixzz4jvtrAnNm"}
        };
    }
}
