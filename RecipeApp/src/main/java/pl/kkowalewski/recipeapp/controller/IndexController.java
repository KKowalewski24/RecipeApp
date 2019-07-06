package pl.kkowalewski.recipeapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

    /*------------------------ FIELDS REGION ------------------------*/
    public static final String DESCRIPTION = "index";

    /*------------------------ METHODS REGION ------------------------*/
    @RequestMapping({"", "/", "/index"})
    public String getIndexPage() {
        return DESCRIPTION;
    }
}
