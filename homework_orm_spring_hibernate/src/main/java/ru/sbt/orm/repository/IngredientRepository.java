package ru.sbt.orm.repository;

import org.springframework.data.repository.CrudRepository;
import ru.sbt.orm.model.Ingredient;
import ru.sbt.orm.model.Recipe;

import java.util.List;

public interface IngredientRepository extends CrudRepository<Ingredient, Long> {
    List<Ingredient> findByRecipe(Recipe recipe);
}
