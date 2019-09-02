package pl.kkowalewski.recipeapp.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import pl.kkowalewski.recipeapp.command.RecipeCommand;
import pl.kkowalewski.recipeapp.model.Recipe;
import pl.kkowalewski.recipeapp.service.image.ImageService;
import pl.kkowalewski.recipeapp.service.recipe.RecipeService;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(MockitoJUnitRunner.class)
public class ImageControllerTest {

    /*------------------------ FIELDS REGION ------------------------*/
    private static final Long RECIPE_ID = 1L;
    private static final String PATH = "/" + ImageController.RECIPE + "/1/" + ImageController.IMAGE;
    private static final String TEXT = "sample text";

    @Mock
    private RecipeService recipeService;

    @Mock
    private ImageService imageService;

    @InjectMocks
    private ImageController imageController;

    private MockMvc mockMvc;

    /*------------------------ METHODS REGION ------------------------*/
    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(imageController).build();
    }

    private RecipeCommand prepareRecipeCommand(Long id) {
        return new RecipeCommand(id);
    }

    private RecipeCommand prepareRecipeCommand(Long id, Byte[] image) {
        RecipeCommand recipeCommand = new RecipeCommand(id);
        recipeCommand.setImage(image);

        return recipeCommand;
    }

    @Test
    public void showUploadFormTest() throws Exception {
        when(recipeService.findCommandById(anyLong())).thenReturn(prepareRecipeCommand(RECIPE_ID));

        mockMvc.perform(get(PATH))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists(ImageController.RECIPE));

        verify(recipeService).findCommandById(anyLong());
    }

    @Test
    public void handleImagePostTest() throws Exception {
        MockMultipartFile multipartFile = new MockMultipartFile("imagefile",
                "testing.txt", "text/plain", "Kamil Kowalewski".getBytes());

        mockMvc.perform(multipart(PATH).file(multipartFile))
                .andExpect(status().is3xxRedirection())
                .andExpect(header().string("Location",
                        "/" + ImageController.RECIPE + "/1/" + ImageController.SHOW));

        verify(imageService).saveImageFile(anyLong(), any());
    }

    @Test
    public void renderImageFromDBTest() throws Exception {
        Byte[] textArray = new Byte[TEXT.getBytes().length];

        for (int i = 0; i < TEXT.getBytes().length; i++) {
            textArray[i] = TEXT.getBytes()[i];
        }

        when(recipeService.findCommandById(anyLong()))
                .thenReturn(prepareRecipeCommand(RECIPE_ID, textArray));

        MockHttpServletResponse response = mockMvc
                .perform(get("/" + ImageController.RECIPE + "/1/"
                        + ImageController.RECIPE_IMAGE))
                .andExpect(status().isOk())
                .andReturn().getResponse();

        assertEquals(TEXT.getBytes().length, response.getContentAsByteArray().length);
    }
}
