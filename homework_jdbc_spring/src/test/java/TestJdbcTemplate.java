import ru.sbt.config.JdbcTemplateConfig;

import ru.sbt.dao.RecipeDAO;
import ru.sbt.model.Ingredient;
import ru.sbt.model.Recipe;


import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.jdbc.JdbcTestUtils;
import java.sql.SQLException;
import java.util.List;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = JdbcTemplateConfig.class)
public class TestJdbcTemplate {

    @BeforeAll
    public static void startServer() throws SQLException {
        //Server.createTcpServer().start();
    }

    @Autowired
    public RecipeDAO recipeDAO;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @AfterEach
    public void clear() {
        JdbcTestUtils.deleteFromTables(jdbcTemplate, "Ingredients", "Recipes");
    }

    @Test
    public void createRecipe(){
        Recipe sup = new Recipe("Рисовый суп");
        sup.addIngredients(new Ingredient("Рис", "100гр"));
        sup.addIngredients(new Ingredient("Лук", "1 шт"));
        sup.addIngredients(new Ingredient("Картофель", "150 гр"));
        recipeDAO.createRecipe(sup);

        List<Recipe> recipes = recipeDAO.findRecipe("Рисовый");
        recipes.forEach(recipe -> System.out.println(recipe.toString()));

    }


}
