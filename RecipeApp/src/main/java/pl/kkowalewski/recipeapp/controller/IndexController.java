package pl.kkowalewski.recipeapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.kkowalewski.recipeapp.model.Category;
import pl.kkowalewski.recipeapp.model.UnitOfMeasure;
import pl.kkowalewski.recipeapp.repository.CategoryRepository;
import pl.kkowalewski.recipeapp.repository.UnitOfMeasureRepository;

import java.util.Optional;

@Controller
public class IndexController {

    /*------------------------ FIELDS REGION ------------------------*/
    public static final String DESCRIPTION = "index";

    private CategoryRepository categoryRepository;
    private UnitOfMeasureRepository unitOfMeasureRepository;

    /*------------------------ METHODS REGION ------------------------*/
    public IndexController(CategoryRepository categoryRepository,
                           UnitOfMeasureRepository unitOfMeasureRepository) {
        this.categoryRepository = categoryRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
    }

    @RequestMapping({"", "/", "/index"})
    public String getIndexPage() {
        Optional<Category> categoryOptional = categoryRepository.findByDescription("American");
        Optional<UnitOfMeasure> unitOfMeasureOptional =
                unitOfMeasureRepository.findByDescription("Teaspoon");

        System.out.println("Category ID is: " + categoryOptional.get().getId());
        System.out.println("UnitOfMeasure ID is: " + unitOfMeasureOptional.get().getId());

        return DESCRIPTION;
    }
}
