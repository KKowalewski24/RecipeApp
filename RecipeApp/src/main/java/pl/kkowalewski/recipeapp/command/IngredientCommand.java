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
    private Long recipeId;
    private String description;
    private BigDecimal amount;
    private UnitOfMeasureCommand uom;

    /*------------------------ METHODS REGION ------------------------*/
    public IngredientCommand(Long id) {
        this.id = id;
    }

    public IngredientCommand(Long id, String description, BigDecimal amount,
                             UnitOfMeasureCommand uom) {
        this.id = id;
        this.description = description;
        this.amount = amount;
        this.uom = uom;
    }
}
