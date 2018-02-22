package guru.springframework.controllers;

import guru.springframework.commands.RecipeCommand;
import guru.springframework.service.ImageService;
import guru.springframework.service.RecipeService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(MockitoJUnitRunner.class)
public class ImageControllerTest {

    @Mock
    ImageService mockImageService;

    @Mock
    RecipeService recipeService;

    @InjectMocks
    ImageController imageController;

    MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(imageController).build();
    }

    @Test
    public void testShowUploadForm() throws Exception {

        RecipeCommand recipeCommand = new RecipeCommand();
        recipeCommand.setId(1L);

        Mockito.when(recipeService.findCommandById(anyLong())).thenReturn(recipeCommand);

        mockMvc.perform(get("/recipe/1/image"))
                .andExpect(status().isOk())
                .andExpect(view().name("recipe/imageuploadform"))
                .andExpect(model().attributeExists("recipe"));

    }

    @Test
    public void testHandleImageUpload() throws Exception {

        MockMultipartFile multipartFile =
                new MockMultipartFile("imagefile", "testing.txt", "text/plain",
                        "Spring Framework Guru".getBytes());

        mockMvc.perform(multipart("/recipe/1/image").file(multipartFile))
                .andExpect(status().is3xxRedirection())
                .andExpect(header().string("Location", "/recipe/1/show"));

        verify(mockImageService, times(1)).saveImageFile(anyLong(), any());


    }
}