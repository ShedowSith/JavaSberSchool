package ru.sbt.model;

import java.util.ArrayList;
import java.util.List;

public class Recipe {
    private Integer id;
    private String name;
    private List<Ingredient> ingredients = new ArrayList<>();

    public Recipe(Integer id, String name) {
        this.id = id;
        this.name = name;
    }
    public Recipe(String name) {
        this.name = name;
    }
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }
    public void addIngredients(Ingredient ingredient) {
        this.ingredients.add(ingredient);
    }

    @Override
    public String toString() {
        return "Recipe{" +
                "id=" + id +
                ", name='" + name + "'\n" +
                ", ingredients=" + ingredients +
                '}';
    }
}
