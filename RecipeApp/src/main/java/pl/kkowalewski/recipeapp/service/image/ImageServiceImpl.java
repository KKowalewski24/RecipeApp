package pl.kkowalewski.recipeapp.service.image;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ImageServiceImpl implements ImageService {

    /*------------------------ FIELDS REGION ------------------------*/

    /*------------------------ METHODS REGION ------------------------*/
    @Override
    public void saveImageFile(Long recipeId, MultipartFile file) {
        //TODO
    }
}
