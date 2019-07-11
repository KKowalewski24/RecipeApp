package pl.kkowalewski.recipeapp.repository;

import org.springframework.data.repository.CrudRepository;
import pl.kkowalewski.recipeapp.model.UnitOfMeasure;

import java.util.Optional;

public interface UnitOfMeasureRepository extends CrudRepository<UnitOfMeasure, Long> {
    Optional<UnitOfMeasure> findByDescription(String description);
}
