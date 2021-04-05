package ru.sbt.orm;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class JdbcRecipeRunner {
    public static void main(String[] args) {
        SpringApplication.run(JdbcRecipeRunner.class, args);
    }
//    public static void main(String[] args) {
//        ApplicationContext context = new AnnotationConfigApplicationContext(JdbcTemplateConfig.class);
//        RecipeDAO recipeDAO = context.getBean(RecipeDAO.class);
//        Recipe sup = new Recipe("Рисовый суп");
//        sup.addIngredients(new Ingredient("Рис", "100гр"));
//        sup.addIngredients(new Ingredient("Лук", "1 шт"));
//        sup.addIngredients(new Ingredient("Картофель", "150 гр"));
//        recipeDAO.createRecipe(sup);
//
//        Recipe sup2 = new Recipe("Гречневый суп");
//        sup2.addIngredients(new Ingredient("Гречка", "100гр"));
//        sup2.addIngredients(new Ingredient("Лук", "1 шт"));
//        sup2.addIngredients(new Ingredient("Картофель", "150 гр"));
//        sup2.addIngredients(new Ingredient("Плавленый сыр", "50 гр"));
//        recipeDAO.createRecipe(sup2);
//
//        List<Recipe> recipes = recipeDAO.findRecipe("суп");
//        recipes.forEach(recipe -> System.out.println(recipe.toString()));
//
//    }
}
