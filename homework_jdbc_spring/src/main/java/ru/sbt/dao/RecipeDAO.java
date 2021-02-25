package ru.sbt.dao;

import ru.sbt.model.Recipe;

import java.util.List;

public interface RecipeDAO {
    Recipe createRecipe(Recipe recipe);
    List<Recipe> findRecipe(String name);
    void deleteRecipe(int id);
}
