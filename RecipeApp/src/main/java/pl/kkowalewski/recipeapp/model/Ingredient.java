package pl.kkowalewski.recipeapp.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@EqualsAndHashCode(exclude = {"recipe"})
@Entity
public class Ingredient {

    /*------------------------ FIELDS REGION ------------------------*/
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.EAGER)
    private UnitOfMeasure unit;

    @ManyToOne
    private Recipe recipe;

    private String description;
    private BigDecimal amount;

    /*------------------------ METHODS REGION ------------------------*/
    public Ingredient() {
    }

    public Ingredient(String description, BigDecimal amount, UnitOfMeasure unit) {
        this.description = description;
        this.amount = amount;
        this.unit = unit;
    }

    public Ingredient(String description, BigDecimal amount, UnitOfMeasure unit, Recipe recipe) {
        this.description = description;
        this.amount = amount;
        this.unit = unit;
        this.recipe = recipe;
    }

    public Ingredient(Long id, String description, BigDecimal amount, UnitOfMeasure unit) {
        this.unit = unit;
        this.description = description;
        this.amount = amount;
    }
}
