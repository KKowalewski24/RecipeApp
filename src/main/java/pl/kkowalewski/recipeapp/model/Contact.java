package pl.kkowalewski.recipeapp.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@Setter
@EqualsAndHashCode
@Entity
public class Contact {

    /*------------------------ FIELDS REGION ------------------------*/
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String pageName;
    private String author;
    private String copyright;
    private String description;

    /*------------------------ METHODS REGION ------------------------*/
    public Contact() {
    }

    public Contact(String pageName, String author, String copyright, String description) {
        this.pageName = pageName;
        this.author = author;
        this.copyright = copyright;
        this.description = description;
    }
}
