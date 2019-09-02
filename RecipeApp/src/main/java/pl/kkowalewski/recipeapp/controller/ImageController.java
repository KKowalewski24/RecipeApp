package pl.kkowalewski.recipeapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import pl.kkowalewski.recipeapp.exception.ImageNotSavedException;
import pl.kkowalewski.recipeapp.service.image.ImageService;
import pl.kkowalewski.recipeapp.service.recipe.RecipeService;

@Controller
public class ImageController {

    /*------------------------ FIELDS REGION ------------------------*/
    public static final String RECIPE = "recipe";
    public static final String REDIRECT = "redirect:/";
    public static final String SHOW = "show";
    public static final String IMAGE = "image";
    public static final String IMAGE_UPLOAD_FORM = "imageuploadform";
    public static final String IMAGE_FILE = "imagefile";

    public static final String SHOW_UPLOAD_FORM = RECIPE + "/{id}/" + IMAGE;
    public static final String HANDLE_IMAGE_POST = RECIPE + "/{id}/" + IMAGE;

    public final RecipeService recipeService;
    public final ImageService imageService;

    /*------------------------ METHODS REGION ------------------------*/
    public ImageController(RecipeService recipeService, ImageService imageService) {
        this.recipeService = recipeService;
        this.imageService = imageService;
    }

    @GetMapping(SHOW_UPLOAD_FORM)
    public String showUploadForm(@PathVariable String id, Model model) {
        model.addAttribute(RECIPE, recipeService.findCommandById(Long.valueOf(id)));

        return RECIPE + "/" + IMAGE_UPLOAD_FORM;
    }

    @PostMapping(HANDLE_IMAGE_POST)
    public String handleImagePost(@PathVariable String id,
                                  @RequestParam(IMAGE_FILE) MultipartFile file) throws ImageNotSavedException {
        imageService.saveImageFile(Long.valueOf(id), file);

        return REDIRECT + RECIPE + "/" + id + "/" + SHOW;
    }
}
