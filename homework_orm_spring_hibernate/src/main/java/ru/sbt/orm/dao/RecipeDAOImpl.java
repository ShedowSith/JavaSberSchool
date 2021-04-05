package ru.sbt.orm.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionTemplate;
import ru.sbt.orm.model.Ingredient;
import ru.sbt.orm.model.Recipe;
import ru.sbt.orm.repository.IngredientRepository;
import ru.sbt.orm.repository.RecipeRepository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.function.Consumer;

@Service
public class RecipeDAOImpl implements RecipeDAO {

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private TransactionTemplate transactionTemplate;

    @Autowired
    private RecipeRepository recipeRepository;

    @Autowired
    private IngredientRepository ingredientRepository;

    private void doInTransaction(Consumer<EntityManager> consumer) {
        transactionTemplate.executeWithoutResult(transactionStatus -> consumer.accept(entityManager));
    }

    @Override
    public void createRecipe(Recipe recipe) {
        doInTransaction(em -> {
            em.persist(recipe);
            recipe.getIngredients().forEach(ingredient -> {
                ingredient.setRecipe(recipe);
                em.persist(ingredient);
            });
        });
    }

    @Override
    @Transactional
    public List<Recipe> findRecipe(String name) {
        List<Recipe> recipes = entityManager.createQuery("select r from Recipe r where r.name like :name", Recipe.class)
                .setParameter("name", "%"+name+"%").getResultList();
        if (recipes.isEmpty()){
            throw new IllegalArgumentException("Рецепт не найден");
        }
        recipes.forEach(recipe -> {
            List<Ingredient> ingredients = entityManager.createQuery("select i from Ingredient i where i.recipe = :recipe", Ingredient.class)
                    .setParameter("recipe", recipe).getResultList();
            recipe.setIngredients(ingredients);
        });
        return recipes;
    }


    @Override
    @Transactional
    public List<Recipe> findRecipeRepositoryName(String name) {
        List<Recipe> recipes = recipeRepository.findByNameLike("%"+name+"%");
        if (recipes.isEmpty()){
            throw new IllegalArgumentException("Рецепт не найден");
        }
        recipes.forEach(recipe -> {
            List<Ingredient> ingredients = ingredientRepository.findByRecipe(recipe);
            recipe.setIngredients(ingredients);
        });
        return recipes;
    }

    @Override
    @Transactional
    public void deleteRecipe(int id) {
        Recipe recipe = entityManager.find(Recipe.class, id);
        if(recipe != null){
            List<Ingredient> ingredients = entityManager.find(Ingredient.class, recipe.getId()).getRecipe().getIngredients();
            ingredients.forEach(ingredient -> {
                entityManager.remove(ingredient);
            });
            entityManager.remove(recipe);
        }
    }
}
