package pl.kkowalewski.recipeapp.service.image;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import pl.kkowalewski.recipeapp.exception.ImageNotSavedException;
import pl.kkowalewski.recipeapp.model.Recipe;
import pl.kkowalewski.recipeapp.repository.RecipeRepository;

import java.io.IOException;

@Service
public class ImageServiceImpl implements ImageService {

    /*------------------------ FIELDS REGION ------------------------*/
    private final RecipeRepository recipeRepository;

    /*------------------------ METHODS REGION ------------------------*/
    public ImageServiceImpl(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    @Transactional
    @Override
    public void saveImageFile(Long recipeId, MultipartFile file) throws ImageNotSavedException {
        try {
            Recipe recipe = recipeRepository.findById(recipeId).get();
            Byte[] fileBytesArray = new Byte[file.getBytes().length];

            for (int i = 0; i < file.getBytes().length; i++) {
                fileBytesArray[i] = file.getBytes()[i];
            }

//          IN CASE OF ISSUES REPLACE ABOVE LOOP THIS ONE
//          int i = 0;
//          for (byte b : file.getBytes()) {
//              fileBytesArray[i++] = b;
//          }

            recipe.setImage(fileBytesArray);
            recipeRepository.save(recipe);
        } catch (IOException e) {
            throw new ImageNotSavedException();
        }
    }
}
