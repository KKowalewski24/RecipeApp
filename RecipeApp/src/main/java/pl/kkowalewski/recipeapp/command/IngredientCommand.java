package pl.kkowalewski.recipeapp.command;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class IngredientCommand {

    /*------------------------ FIELDS REGION ------------------------*/
    private Long id;
    private String description;
    private BigDecimal amount;
    private UnitOfMeasureCommand unitOfMeasure;

    /*------------------------ METHODS REGION ------------------------*/

}
