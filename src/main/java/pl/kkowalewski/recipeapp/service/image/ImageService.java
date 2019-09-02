package pl.kkowalewski.recipeapp.service.image;

import org.springframework.web.multipart.MultipartFile;
import pl.kkowalewski.recipeapp.exception.ImageNotSavedException;

public interface ImageService {
    void saveImageFile(Long recipeId, MultipartFile file) throws ImageNotSavedException;
}
