package pl.kkowalewski.recipeapp.repository;

import org.springframework.data.repository.CrudRepository;
import pl.kkowalewski.recipeapp.model.Category;

import java.util.Optional;

public interface CategoryRepository extends CrudRepository<Category, Long> {
    Optional<Category> findByDescription(String description);
}
