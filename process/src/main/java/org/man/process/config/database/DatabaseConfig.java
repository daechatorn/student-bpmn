package org.man.process.config.database;

import com.zaxxer.hikari.HikariDataSource;
import org.camunda.bpm.engine.impl.cfg.AbstractProcessEnginePlugin;
import org.camunda.bpm.engine.impl.cfg.ProcessEngineConfigurationImpl;
import org.springframework.boot.autoconfigure.flyway.FlywayMigrationInitializer;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import javax.sql.DataSource;

@Configuration
public class DatabaseConfig {

    @Bean
    @ConfigurationProperties("spring.datasource")
    public HikariDataSource dataSource() {
        return (HikariDataSource) DataSourceBuilder.create().build();
    }

    @Bean
    public NamedParameterJdbcTemplate namedParameterJdbcTemplate(DataSource dataSource) {
        return new NamedParameterJdbcTemplate(dataSource);
    }

    //To run flyway migration before the bpm engine gets created (also trying to read database)
    @Bean
    AbstractProcessEnginePlugin flywayInitializingProcessEnginePlugin(FlywayMigrationInitializer initializer) {
        return new AbstractProcessEnginePlugin() {
            @Override
            public void postInit(ProcessEngineConfigurationImpl processEngineConfiguration) {
            }
        };
    }
}
