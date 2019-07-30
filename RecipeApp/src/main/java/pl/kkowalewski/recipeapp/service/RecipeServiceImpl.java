package pl.kkowalewski.recipeapp.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.kkowalewski.recipeapp.model.Recipe;
import pl.kkowalewski.recipeapp.repository.RecipeRepository;

import java.util.HashSet;
import java.util.Set;
@Slf4j
@Service
public class RecipeServiceImpl implements RecipeService {

    /*------------------------ FIELDS REGION ------------------------*/
    private final RecipeRepository recipeRepository;

    /*------------------------ METHODS REGION ------------------------*/
    public RecipeServiceImpl(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    @Override
    public Set<Recipe> prepareRecipeSet() {
        log.debug("log debug");
        Set<Recipe> recipes = new HashSet<>();
        recipeRepository.findAll().iterator().forEachRemaining(recipes::add);

        return recipes;
    }
}
