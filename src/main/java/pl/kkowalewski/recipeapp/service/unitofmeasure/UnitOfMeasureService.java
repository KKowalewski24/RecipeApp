package pl.kkowalewski.recipeapp.service.unitofmeasure;

import pl.kkowalewski.recipeapp.command.UnitOfMeasureCommand;

import java.util.Set;

public interface UnitOfMeasureService {
    Set<UnitOfMeasureCommand> listAllUoms();
}
