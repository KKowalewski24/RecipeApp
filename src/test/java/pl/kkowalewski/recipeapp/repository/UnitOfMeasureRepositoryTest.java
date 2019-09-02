package pl.kkowalewski.recipeapp.repository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;
import pl.kkowalewski.recipeapp.model.UnitOfMeasure;

import java.util.Optional;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@DataJpaTest
public class UnitOfMeasureRepositoryTest {

    /*------------------------ FIELDS REGION ------------------------*/
    @Autowired
    private UnitOfMeasureRepository unitOfMeasureRepository;

    /*------------------------ METHODS REGION ------------------------*/
    @Test
    public void findByDescription() {
        String description = "Teaspoon";
        Optional<UnitOfMeasure> unitOfMeasureOptional =
                unitOfMeasureRepository.findByDescription(description);

        assertEquals(description, unitOfMeasureOptional.get().getDescription());
    }
}
    