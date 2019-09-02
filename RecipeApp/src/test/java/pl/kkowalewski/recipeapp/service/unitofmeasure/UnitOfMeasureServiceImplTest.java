package pl.kkowalewski.recipeapp.service.unitofmeasure;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import pl.kkowalewski.recipeapp.converter.tocommand.UnitOfMeasureToUnitOfMeasureCommand;
import pl.kkowalewski.recipeapp.model.UnitOfMeasure;
import pl.kkowalewski.recipeapp.repository.UnitOfMeasureRepository;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UnitOfMeasureServiceImplTest {

    /*------------------------ FIELDS REGION ------------------------*/
    private final static Long UOM_ONE_ID = 1L;
    private final static Long UOM_TWO_ID = 2L;

    @Mock
    private UnitOfMeasureRepository unitOfMeasureRepository;

    private UnitOfMeasureToUnitOfMeasureCommand unitOfMeasureToUnitOfMeasureCommand =
            new UnitOfMeasureToUnitOfMeasureCommand();

    private UnitOfMeasureService unitOfMeasureService;

    /*------------------------ METHODS REGION ------------------------*/
    @Before
    public void setUp() {
        unitOfMeasureService = new UnitOfMeasureServiceImpl(
                unitOfMeasureRepository, unitOfMeasureToUnitOfMeasureCommand);
    }

    private Set<UnitOfMeasure> prepareUnitOfMeasureSet(Long... longs) {
        Set<UnitOfMeasure> unit = new HashSet<>();

        for (Long it : longs) {
            unit.add(new UnitOfMeasure(it));
        }

        return unit;
    }

    @Test
    public void listAllUomsTest() {
        when(unitOfMeasureRepository.findAll())
                .thenReturn(prepareUnitOfMeasureSet(UOM_ONE_ID, UOM_TWO_ID));

        assertEquals(2, unitOfMeasureService.listAllUoms().size());
        verify(unitOfMeasureRepository).findAll();
    }
}
