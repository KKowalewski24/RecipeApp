package pl.kkowalewski.recipeapp.service.unitofmeasure;

import org.springframework.stereotype.Service;
import pl.kkowalewski.recipeapp.command.UnitOfMeasureCommand;
import pl.kkowalewski.recipeapp.converter.tocommand.UnitOfMeasureToUnitOfMeasureCommand;
import pl.kkowalewski.recipeapp.repository.UnitOfMeasureRepository;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class UnitOfMeasureServiceImpl implements UnitOfMeasureService {

    /*------------------------ FIELDS REGION ------------------------*/
    private final UnitOfMeasureRepository unitOfMeasureRepository;
    private final UnitOfMeasureToUnitOfMeasureCommand unitOfMeasureToUnitOfMeasureCommand;

    /*------------------------ METHODS REGION ------------------------*/
    public UnitOfMeasureServiceImpl(
            UnitOfMeasureRepository unitOfMeasureRepository,
            UnitOfMeasureToUnitOfMeasureCommand unitOfMeasureToUnitOfMeasureCommand) {
        this.unitOfMeasureRepository = unitOfMeasureRepository;
        this.unitOfMeasureToUnitOfMeasureCommand = unitOfMeasureToUnitOfMeasureCommand;
    }

    @Override
    public Set<UnitOfMeasureCommand> listAllUoms() {
        return StreamSupport.stream(unitOfMeasureRepository.findAll()
                .spliterator(), false)
                .map(unitOfMeasureToUnitOfMeasureCommand::convert)
                .collect(Collectors.toSet());
    }
}
