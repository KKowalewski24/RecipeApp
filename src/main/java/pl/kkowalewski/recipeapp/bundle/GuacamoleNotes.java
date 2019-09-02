package pl.kkowalewski.recipeapp.bundle;

import java.util.ListResourceBundle;

public class GuacamoleNotes extends ListResourceBundle {

    /*------------------------ FIELDS REGION ------------------------*/

    /*------------------------ METHODS REGION ------------------------*/
    @Override
    protected Object[][] getContents() {
        return new Object[][]{
                {"_description", "For a very quick guacamole just take a 1/4 cup of salsa and mix" +
                        " it in with " +
                        "your mashed avocados.\n" + "Feel free to experiment! One classic Mexican" +
                        " guacamole has pomegranate seeds and chunks of peaches in it (a Diana " +
                        "Kennedy favorite). Try guacamole with added pineapple, mango, or " +
                        "strawberries.\n" + "The simplest version of guacamole is just mashed " +
                        "avocados with salt. Don't let the lack of availability of other " +
                        "ingredients stop you from making guacamole.\n" + "To extend a limited " +
                        "supply of avocados, add either sour cream or cottage cheese to your " +
                        "guacamole dip. Purists may be horrified, but so what? It tastes great" +
                        ".\n" + "\n" + "\n" + "Read more: http://www.simplyrecipes" +
                        ".com/recipes/perfect_guacamole/#ixzz4jvoun5ws"}
        };
    }
}
