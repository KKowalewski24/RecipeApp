package pl.kkowalewski.recipeapp.service.image;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;
import pl.kkowalewski.recipeapp.model.Recipe;
import pl.kkowalewski.recipeapp.repository.RecipeRepository;

import java.io.IOException;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ImageServiceImplTest {

    /*------------------------ FIELDS REGION ------------------------*/
    private static final Long RECIPE_ID = 1L;

    @Mock
    private RecipeRepository recipeRepository;

    private ImageService imageService;

    /*------------------------ METHODS REGION ------------------------*/
    @Before
    public void setUp() {
        imageService = new ImageServiceImpl(recipeRepository);
    }

    private Recipe prepareRecipe(Long id) {
        return new Recipe(id);
    }

    @Test
    public void saveImageFileTest() throws IOException {
        MultipartFile multipartFile = new MockMultipartFile("imagefile",
                "testing.txt", "text/plain", "Kamil Kowalewski".getBytes());

        when(recipeRepository.findById(anyLong())).thenReturn(Optional.of(prepareRecipe(RECIPE_ID)));

        ArgumentCaptor<Recipe> argumentCaptor = ArgumentCaptor.forClass(Recipe.class);

        imageService.saveImageFile(RECIPE_ID, multipartFile);

        verify(recipeRepository).save(argumentCaptor.capture());
        assertEquals(multipartFile.getBytes().length, argumentCaptor.getValue().getImage().length);
    }
}
