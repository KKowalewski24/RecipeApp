package pl.kkowalewski.recipeapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import pl.kkowalewski.recipeapp.model.Contact;

import static pl.kkowalewski.recipeapp.constant.AppConstants.AUTHOR;
import static pl.kkowalewski.recipeapp.constant.AppConstants.CONTACT;
import static pl.kkowalewski.recipeapp.constant.AppConstants.COPYRIGHT;
import static pl.kkowalewski.recipeapp.constant.AppConstants.DESCRIPTION;
import static pl.kkowalewski.recipeapp.constant.AppConstants.PAGE_NAME;
import static pl.kkowalewski.recipeapp.constant.AppConstants.PATH_CONTACT;

@Controller
public class ContactController {

    /*------------------------ FIELDS REGION ------------------------*/

    /*------------------------ METHODS REGION ------------------------*/
    @GetMapping(PATH_CONTACT)
    public String contact(Model model) {
        model.addAttribute(CONTACT, new Contact(PAGE_NAME, AUTHOR, COPYRIGHT, DESCRIPTION));

        return CONTACT;
    }
}
