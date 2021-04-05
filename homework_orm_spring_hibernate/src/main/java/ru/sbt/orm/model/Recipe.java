package ru.sbt.orm.model;

import lombok.*;
import org.springframework.data.annotation.AccessType;
import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@DiscriminatorValue("Recipe")
@AccessType(AccessType.Type.FIELD)
@Data
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
public class Recipe implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Basic
    private String name;
    @OneToMany(mappedBy = "recipe", fetch = FetchType.LAZY)
    private List<Ingredient> ingredients = new ArrayList<>();


    @Override
    public String toString() {
        return "Recipe{" +
                "id=" + id +
                ", name='" + name + "'\n" +
                ", ingredients=" + ingredients +
                '}';
    }
}
