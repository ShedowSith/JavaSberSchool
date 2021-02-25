package ru.sbt.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.lob.LobHandler;
import ru.sbt.dao.RecipeDAO;
import ru.sbt.dao.RecipeDAOImpl;

import javax.sql.DataSource;

@Configuration
@Import(DataConfiguration.class)
public class JdbcTemplateConfig {

    @Bean
    public RecipeDAO recipeDao(DataSource dataSource, JdbcTemplate jdbcTemplate) {
        return new RecipeDAOImpl(dataSource, jdbcTemplate);
    }
}
