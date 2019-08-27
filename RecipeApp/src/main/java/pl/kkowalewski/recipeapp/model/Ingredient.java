package pl.kkowalewski.recipeapp.model;

import lombok.Data;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) return false;

        Ingredient that = (Ingredient) o;

        return new EqualsBuilder()
                .append(id, that.id)
                .append(unit, that.unit)
                .append(description, that.description)
                .append(amount, that.amount)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(id)
                .append(unit)
                .append(description)
                .append(amount)
                .toHashCode();
    }
}
