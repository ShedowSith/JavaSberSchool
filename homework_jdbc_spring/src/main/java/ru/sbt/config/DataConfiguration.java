package ru.sbt.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.init.ScriptUtils;
import org.springframework.jdbc.support.lob.DefaultLobHandler;
import org.springframework.jdbc.support.lob.LobHandler;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.sql.SQLException;

@Configuration
public class DataConfiguration {
    @Bean
    public DataSource dataSource() {
        return new DriverManagerDataSource("jdbc:h2:~/test", "user", "");
    }

    @Bean
    public JdbcTemplate jdbcTemplate(DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

    @Bean
    public LobHandler lobHandler() {
        return new DefaultLobHandler();
    }

    @PostConstruct
    public void makeScript() throws SQLException {
        ScriptUtils.executeSqlScript(dataSource().getConnection(), new ClassPathResource("/data.sql"));
    }
}
