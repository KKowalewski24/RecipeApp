package pl.kkowalewski.recipeapp.service;

import org.springframework.stereotype.Service;
import pl.kkowalewski.recipeapp.exception.RecipeNotFoundException;
import pl.kkowalewski.recipeapp.model.Recipe;
import pl.kkowalewski.recipeapp.repository.RecipeRepository;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

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
        Set<Recipe> recipes = new HashSet<>();
        recipeRepository.findAll().iterator().forEachRemaining(recipes::add);

        return recipes;
    }

    @Override
    public Recipe findById(Long id) {
        Optional<Recipe> recipeOptional = recipeRepository.findById(id);

        if (!recipeOptional.isPresent()) {
            throw new RecipeNotFoundException();
        }

        return recipeOptional.get();
    }
}
