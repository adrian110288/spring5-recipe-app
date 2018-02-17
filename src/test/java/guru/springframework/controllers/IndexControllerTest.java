package guru.springframework.controllers;

import guru.springframework.domain.Recipe;
import guru.springframework.service.RecipeService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

public class IndexControllerTest {

    @Mock
    RecipeService mockRecipeService;
    @Mock
    Model mockModel;

    Set<Recipe> recipeList;
    IndexController target;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        target = new IndexController(mockRecipeService);

        recipeList = new HashSet<>();
        Recipe recipe = new Recipe();
        recipeList.add(recipe);

    }

    @Test
    public void testMockMvc() throws Exception {

        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(target).build();
        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("index"));
    }

    @Test
    public void getIndexPage() {

        final String expectedTemplateName = "index";
        final String recipesAttributeName = "recipes";

//        given
        when(mockRecipeService.getRecipes()).thenReturn(recipeList);

//        when
        String templateName = target.getIndexPage(mockModel);

//        then
        assertEquals(expectedTemplateName, templateName);
//        assertTrue(mockModel.containsAttribute(recipesAttributeName));
        verify(mockModel).addAttribute(eq(recipesAttributeName), eq(recipeList));
    }
}