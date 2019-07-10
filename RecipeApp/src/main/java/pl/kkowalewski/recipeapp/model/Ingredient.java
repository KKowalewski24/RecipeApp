package pl.kkowalewski.recipeapp.model;

import org.hibernate.annotations.Fetch;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import java.math.BigDecimal;

@Entity
public class Ingredient {

    /*------------------------ FIELDS REGION ------------------------*/
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;
    private BigDecimal amount;
    @OneToOne(fetch = FetchType.EAGER)
    private UnitOfMeasure unit;
    @ManyToOne
    private Recipe recipe;

    /*------------------------ METHODS REGION ------------------------*/
    public Ingredient(final String description, final BigDecimal amount, final UnitOfMeasure unit,
                      final Recipe recipe) {
        this.description = description;
        this.amount = amount;
        this.unit = unit;
        this.recipe = recipe;
    }

    public Long getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public UnitOfMeasure getUnit() {
        return unit;
    }

    public Recipe getRecipe() {
        return recipe;
    }
}
