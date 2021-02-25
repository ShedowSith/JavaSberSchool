package ru.sbt.model;

public class Ingredient {
    private Integer recipe_id;
    private String product;
    private String amount;

    public Ingredient(String product, String amount, Integer recipe_id) {
        this.product = product;
        this.amount = amount;
        this.recipe_id = recipe_id;
    }
    public Ingredient(String product, String amount) {
        this.product = product;
        this.amount = amount;
    }
    public Integer getRecipe_id() {
        return recipe_id;
    }

    public void setRecipe_id(Integer recipe_id) {
        this.recipe_id = recipe_id;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "Ingredient{" +
                "product='" + product + '\'' +
                ", amount='" + amount + '\'' +
                "}\n";
    }
}
