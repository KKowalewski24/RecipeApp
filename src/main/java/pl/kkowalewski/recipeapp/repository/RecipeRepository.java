package pl.kkowalewski.recipeapp.repository;

import org.springframework.data.repository.CrudRepository;
import pl.kkowalewski.recipeapp.model.Recipe;

public interface RecipeRepository extends CrudRepository<Recipe, Long> {

}
