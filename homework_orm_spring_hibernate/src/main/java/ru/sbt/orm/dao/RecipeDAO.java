package ru.sbt.orm.dao;

import ru.sbt.orm.model.Recipe;

import java.util.List;

public interface RecipeDAO {
    void createRecipe(Recipe recipe);
    List<Recipe> findRecipe(String name);
    List<Recipe> findRecipeRepositoryName(String name);
    void deleteRecipe(int id);
}
