package pl.kkowalewski.recipeapp.repository;

import org.springframework.data.repository.CrudRepository;
import pl.kkowalewski.recipeapp.model.Category;

public interface CategoryRepository extends CrudRepository<Category, Long> {

}
