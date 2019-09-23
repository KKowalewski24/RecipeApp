package pl.kkowalewski.recipeapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import pl.kkowalewski.recipeapp.model.About;

import static pl.kkowalewski.recipeapp.constant.AppConstants.ABOUT;
import static pl.kkowalewski.recipeapp.constant.AppConstants.DESCRIPTION;
import static pl.kkowalewski.recipeapp.constant.AppConstants.PAGE_NAME;
import static pl.kkowalewski.recipeapp.constant.AppConstants.PATH_ABOUT;

@Controller
public class AboutController {

    /*------------------------ FIELDS REGION ------------------------*/

    /*------------------------ METHODS REGION ------------------------*/
    @GetMapping(PATH_ABOUT)
    public String about(Model model) {
        model.addAttribute(ABOUT, new About(PAGE_NAME, DESCRIPTION));

        return ABOUT;
    }
}
