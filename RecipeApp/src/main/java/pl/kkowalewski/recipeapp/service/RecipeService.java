package pl.kkowalewski.recipeapp.service;

import pl.kkowalewski.recipeapp.model.Recipe;

import java.util.Set;

public interface RecipeService {

    Set<Recipe> prepareRecipeSet();

    Recipe findById(Long id);
}
