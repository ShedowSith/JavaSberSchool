package ru.sbt.orm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.support.TransactionTemplate;
import ru.sbt.orm.dao.RecipeDAO;
import ru.sbt.orm.dao.RecipeDAOImpl;
import ru.sbt.orm.model.Ingredient;
import ru.sbt.orm.model.Recipe;

import javax.persistence.EntityManager;
import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

@SpringBootApplication
public class Runner implements CommandLineRunner {
    @Autowired
    private EntityManager entityManager;

    @Autowired
    private TransactionTemplate transactionTemplate;

    @Autowired
    private RecipeDAO recipeDao;

    @Autowired
    private DataSource dataSource;

    public static void  main(String[] args) {
        SpringApplication.run(Runner.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Recipe supRis = new Recipe();
        supRis.setName("Рисовый суп");
        List<Ingredient> ingredients = new ArrayList<>();
        ingredients.add(Ingredient.createIngredient("Рис", "100гр"));
        ingredients.add(Ingredient.createIngredient("Лук", "1 шт"));
        ingredients.add(Ingredient.createIngredient("Картофель", "150гр"));
        supRis.setIngredients(ingredients);
        recipeDao.createRecipe(supRis);

        Recipe supGrech = new Recipe();
        supGrech.setName("Гречневый суп");
        List<Ingredient> ingredientsSupGrech = new ArrayList<>();
        ingredientsSupGrech.add(Ingredient.createIngredient("Гречка", "100гр"));
        ingredientsSupGrech.add(Ingredient.createIngredient("Лук", "1 шт"));
        ingredientsSupGrech.add(Ingredient.createIngredient("Картофель", "100гр"));
        ingredientsSupGrech.add(Ingredient.createIngredient("Плавленый сыр", "50гр"));
        supGrech.setIngredients(ingredientsSupGrech);
        recipeDao.createRecipe(supGrech);

        List<Recipe> recipes = recipeDao.findRecipe("суп");
        recipes.forEach(recipe -> System.out.println(recipe.toString()));


        recipeDao.deleteRecipe(1);
        System.out.println("Удалили рецепт id = 1");
        recipes = recipeDao.findRecipeRepositoryName("Гречневый суп");
        recipes.forEach(recipe -> System.out.println(recipe.toString()));

    }
}
