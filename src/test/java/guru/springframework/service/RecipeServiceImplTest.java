package guru.springframework.service;

import guru.springframework.converters.RecipeCommandToRecipe;
import guru.springframework.converters.RecipeToRecipeCommand;
import guru.springframework.domain.Recipe;
import guru.springframework.exceptions.NotFoundException;
import guru.springframework.repositories.RecipeRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

public class RecipeServiceImplTest {

    RecipeServiceImpl recipeService;
    @Mock
    RecipeRepository recipeRepository;

    @Mock
    RecipeToRecipeCommand recipeToRecipeCommand;

    @Mock
    RecipeCommandToRecipe recipeCommandToRecipe;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        recipeService = new RecipeServiceImpl(recipeRepository, recipeCommandToRecipe, recipeToRecipeCommand);
    }

    @Test
    public void testGetRecipes() {

        Recipe recipe = new Recipe();
        Set<Recipe> recipeList = new HashSet<>();
        recipeList.add(recipe);

        when(recipeRepository.findAll()).thenReturn(recipeList);

        Set<Recipe> recipes = recipeService.getRecipes();

        assertEquals(recipes.size(), 1);
        Mockito.verify(recipeRepository, Mockito.times(1)).findAll();
    }

    @Test
    public void testGetRecipeById() {

        Recipe recipe = new Recipe();
        recipe.setId(1L);
        Optional<Recipe> recipeOptional = Optional.of(recipe);

//        given
        when(recipeRepository.findById(anyLong())).thenReturn(recipeOptional);

//        when
        Recipe recipeReturned = recipeService.findById(1L);

//        then
        assertNotNull(recipeReturned);
        verify(recipeRepository, times(1)).findById(anyLong());
    }

    @Test(expected = RuntimeException.class)
    public void testThrowExceptionWhenRecipeByIdNotExist() {

        Optional<Recipe> recipeOptional = Optional.empty();

//        given
        when(recipeRepository.findById(anyLong())).thenReturn(recipeOptional);

//        when
        Recipe recipe = recipeService.findById(1L);

    }

    @Test
    public void testDeleteById() {

        final Long idToDelete = 1L;

        recipeService.deleteById(idToDelete);

        verify(recipeRepository, times(1)).deleteById(eq(idToDelete));
    }

    @Test(expected = NotFoundException.class)
    public void testRecipeByIdNotFound() {

        Optional<Recipe> optionalRecipe = Optional.empty();

        when(recipeRepository.findById(anyLong())).thenReturn(optionalRecipe);

        Recipe recipeReturned = recipeService.findById(1L);

    }
}