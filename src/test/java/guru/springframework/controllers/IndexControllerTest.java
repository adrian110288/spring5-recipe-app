package guru.springframework.controllers;

import guru.springframework.domain.Recipe;
import guru.springframework.service.RecipeService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class IndexControllerTest {

    @Mock
    RecipeService mockRecipeService;
    @Mock
    Model mockModel;

    Set<Recipe> recipeList;
    IndexController target;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        target = new IndexController(mockRecipeService);

        recipeList = new HashSet<>();
        Recipe recipe = new Recipe();
        recipeList.add(recipe);

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