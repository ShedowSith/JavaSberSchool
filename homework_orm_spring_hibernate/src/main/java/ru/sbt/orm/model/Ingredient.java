package ru.sbt.orm.model;

import lombok.*;

import javax.persistence.*;
import org.springframework.data.annotation.AccessType;
import java.io.Serializable;

@Entity
@DiscriminatorValue("Ingredient")
@AccessType(AccessType.Type.FIELD)
@Data
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
public class Ingredient implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Basic
    private String product;
    @Basic
    private String amount;
    @ManyToOne
    @JoinColumn(name = "RECIPES_ID")
    private Recipe recipe;

    public static Ingredient createIngredient(String product, String amount) {
        Ingredient ingredient = new Ingredient();
        ingredient.setProduct(product);
        ingredient.setAmount(amount);
        return ingredient;
    }

    @Override
    public String toString() {
        return "Ingredient{" +
                "product='" + product + '\'' +
                ", amount='" + amount + '\'' +
                "}\n";
    }
}
