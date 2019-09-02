package pl.kkowalewski.recipeapp.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;

@Getter
@Setter
@EqualsAndHashCode(exclude = {"recipe"})
@Entity
public class Ingredient {

    /*------------------------ FIELDS REGION ------------------------*/
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.EAGER)
    private UnitOfMeasure uom;

    @ManyToOne
    private Recipe recipe;

    private String description;
    private BigDecimal amount;

    /*------------------------ METHODS REGION ------------------------*/
    public Ingredient() {
    }

    public Ingredient(Long id) {
        this.id = id;
    }

    public Ingredient(String description, BigDecimal amount, UnitOfMeasure uom) {
        this.description = description;
        this.amount = amount;
        this.uom = uom;
    }

    public Ingredient(String description, BigDecimal amount, UnitOfMeasure unit, Recipe recipe) {
        this.description = description;
        this.amount = amount;
        this.uom = unit;
        this.recipe = recipe;
    }

    public Ingredient(Long id, String description, BigDecimal amount, UnitOfMeasure uom) {
        this.id = id;
        this.uom = uom;
        this.description = description;
        this.amount = amount;
    }

    public Ingredient(Long id, String description, BigDecimal amount, UnitOfMeasure uom,
                      Recipe recipe) {
        this.id = id;
        this.uom = uom;
        this.description = description;
        this.amount = amount;
        this.recipe = recipe;
    }
}
