package guru.springframework.controllers;

import guru.springframework.commands.RecipeCommand;
import guru.springframework.service.RecipeService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class IngredientControllerTest {

    @Mock
    RecipeService recipeService;

    IngredientController target;

    MockMvc mockMvc;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        target = new IngredientController(recipeService);
        mockMvc = MockMvcBuilders.standaloneSetup(target).build();
    }

    @Test
    public void testListIngredients() throws Exception {

//        given
        RecipeCommand recipeCommand = new RecipeCommand();
        Mockito.when(recipeService.findCommandById(anyLong())).thenReturn(recipeCommand);

//        when
        mockMvc.perform(get("/recipe/1/ingredients"))
                .andExpect(status().isOk())
                .andExpect(view().name("recipe/ingredient/list"))
                .andExpect(model().attributeExists("recipe"));

//        then
        Mockito.verify(recipeService, times(1)).findCommandById(anyLong());
    }
}