package pl.kkowalewski.recipeapp.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Entity
public class UnitOfMeasure {

    /*------------------------ FIELDS REGION ------------------------*/
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;

    /*------------------------ METHODS REGION ------------------------*/
    public UnitOfMeasure() {
    }

    public UnitOfMeasure(final String description) {
        this.description = description;
    }

}
