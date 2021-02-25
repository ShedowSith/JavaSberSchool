package ru.sbt.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.*;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.jdbc.core.simple.SimpleJdbcInsertOperations;
import ru.sbt.model.Ingredient;
import ru.sbt.model.Recipe;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class RecipeDAOImpl implements RecipeDAO {
    private JdbcTemplate jdbcTemplate;
    private SimpleJdbcInsertOperations insertOperations;
    private NamedParameterJdbcOperations parameterJdbcOperations;

    public RecipeDAOImpl(DataSource dataSource,
                         JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.parameterJdbcOperations = new NamedParameterJdbcTemplate(dataSource);
        this.insertOperations = new SimpleJdbcInsert(dataSource)
                .withTableName("Recipes")
                .usingGeneratedKeyColumns("id");
    }

    @Override
    public Recipe createRecipe(Recipe recipe) {
        Integer returnKey = (Integer) insertOperations.executeAndReturnKey(new BeanPropertySqlParameterSource(recipe));
        recipe.getIngredients().stream().forEach((ing) -> ing.setRecipe_id(returnKey));
        parameterJdbcOperations.batchUpdate("insert into Ingredients (PRODUCT, AMOUNT, RECIPES_ID) values (:product, :amount, :recipe_id)",
                SqlParameterSourceUtils.createBatch(recipe.getIngredients()));
        return recipe;
    }

    @Override
    public List<Recipe> findRecipe(String name) {
        List<Recipe> recipes = parameterJdbcOperations.query("select ID, NAME from RECIPES where NAME like :name",
                new MapSqlParameterSource("name", "%"+name+"%"),
                new RowMapper<Recipe>(){
                    @Override
                    public Recipe mapRow(ResultSet resultSet, int i) throws SQLException {
                        return new Recipe(resultSet.getInt("ID"), resultSet.getString("NAME"));
                    }
                });
        if (recipes.isEmpty()){
            throw new IllegalArgumentException("Рецепт не найден");
        }
        recipes.forEach(recipe -> {
            List<Ingredient> ingredients = parameterJdbcOperations.query("select ID, PRODUCT, AMOUNT, RECIPES_ID from INGREDIENTS where RECIPES_ID = :ID",
                    new MapSqlParameterSource("ID", recipe.getId()),
                    new RowMapper<Ingredient>(){
                        @Override
                        public Ingredient mapRow(ResultSet resultSet, int i) throws SQLException {
                            return new Ingredient(resultSet.getString("PRODUCT"), resultSet.getString("AMOUNT"), resultSet.getInt("RECIPES_ID"));
                        }
                    });
            recipe.setIngredients(ingredients);

        });

        return recipes;
    }

    @Override
    public void deleteRecipe(int id) {
        String SQL = "DELETE FROM RECIPES WHERE id = ?";
        jdbcTemplate.update(SQL, id);
        String SQL2 = "DELETE FROM INGREDIENTS WHERE RECIPES_ID = ?";
        jdbcTemplate.update(SQL2, id);
    }
}
