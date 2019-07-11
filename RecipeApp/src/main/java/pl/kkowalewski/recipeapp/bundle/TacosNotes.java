package pl.kkowalewski.recipeapp.bundle;

import java.util.ListResourceBundle;

public class TacosNotes extends ListResourceBundle {

    /*------------------------ FIELDS REGION ------------------------*/

    /*------------------------ METHODS REGION ------------------------*/
    @Override
    protected Object[][] getContents() {
        return new Object[][]{
                {"_description", "We have a family motto and it is this: Everything goes better " +
                        "in a tortilla.\n" + "Any and every kind of leftover can go inside a warm" +
                        " tortilla, usually with a healthy dose of pickled jalapenos. I can " +
                        "always sniff out a late-night snacker when the aroma of tortillas " +
                        "heating in a hot pan on the stove comes wafting through the house.\n" +
                        "Today’s tacos are more purposeful – a deliberate meal instead of a " +
                        "secretive midnight snack!\n" + "First, I marinate the chicken briefly in" +
                        " a spicy paste of ancho chile powder, oregano, cumin, and sweet orange " +
                        "juice while the grill is heating. You can also use this time to prepare " +
                        "the taco toppings.\n" + "Grill the chicken, then let it rest while you " +
                        "warm the tortillas. Now you are ready to assemble the tacos and dig in. " +
                        "The whole meal comes together in about 30 minutes!\n" + "\n" + "\n" +
                        "Read more: http://www.simplyrecipes" +
                        ".com/recipes/spicy_grilled_chicken_tacos/#ixzz4jvu7Q0MJ"},
        };
    }
}
