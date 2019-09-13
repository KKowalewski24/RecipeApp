package pl.kkowalewski.recipeapp.controller;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import pl.kkowalewski.recipeapp.command.RecipeCommand;
import pl.kkowalewski.recipeapp.exception.ImageNotSavedException;
import pl.kkowalewski.recipeapp.service.image.ImageService;
import pl.kkowalewski.recipeapp.service.recipe.RecipeService;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.IOException;

import static pl.kkowalewski.recipeapp.constant.AppConstants.IMAGE_FILE;
import static pl.kkowalewski.recipeapp.constant.AppConstants.IMAGE_HANDLE_IMAGE_POST;
import static pl.kkowalewski.recipeapp.constant.AppConstants.IMAGE_RENDER_IMAGE_DB;
import static pl.kkowalewski.recipeapp.constant.AppConstants.IMAGE_SHOW_UPLOAD_FORM;
import static pl.kkowalewski.recipeapp.constant.AppConstants.IMAGE_UPLOAD_FORM;
import static pl.kkowalewski.recipeapp.constant.AppConstants.RECIPE;
import static pl.kkowalewski.recipeapp.constant.AppConstants.REDIRECT;
import static pl.kkowalewski.recipeapp.constant.AppConstants.SHOW;

@Controller
public class ImageController {

    /*------------------------ FIELDS REGION ------------------------*/

    public final RecipeService recipeService;
    public final ImageService imageService;

    /*------------------------ METHODS REGION ------------------------*/
    public ImageController(RecipeService recipeService, ImageService imageService) {
        this.recipeService = recipeService;
        this.imageService = imageService;
    }

    @GetMapping(IMAGE_SHOW_UPLOAD_FORM)
    public String showUploadForm(@PathVariable String id, Model model) {
        model.addAttribute(RECIPE, recipeService.findCommandById(Long.valueOf(id)));

        return RECIPE + "/" + IMAGE_UPLOAD_FORM;
    }

    @PostMapping(IMAGE_HANDLE_IMAGE_POST)
    public String handleImagePost(
            @PathVariable String id,
            @RequestParam(IMAGE_FILE) MultipartFile file) throws ImageNotSavedException {
        imageService.saveImageFile(Long.valueOf(id), file);

        return REDIRECT + RECIPE + "/" + id + "/" + SHOW;
    }

    @GetMapping(IMAGE_RENDER_IMAGE_DB)
    public void renderImageFromDB(@PathVariable String id,
                                  HttpServletResponse response) throws IOException {
        RecipeCommand recipeCommand = recipeService.findCommandById(Long.valueOf(id));

        if (recipeCommand.getImage() != null) {
            byte[] imageByteArray = new byte[recipeCommand.getImage().length];

            for (int i = 0; i < recipeCommand.getImage().length; i++) {
                imageByteArray[i] = recipeCommand.getImage()[i];
            }

            response.setContentType("image/jpeg");
            IOUtils.copy(new ByteArrayInputStream(imageByteArray), response.getOutputStream());

        }
    }
}
