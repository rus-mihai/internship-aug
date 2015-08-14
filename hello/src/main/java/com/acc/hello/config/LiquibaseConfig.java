package com.acc.hello.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import liquibase.integration.spring.SpringLiquibase;

@Configuration
@PropertySource("classpath:application.properties")
public class LiquibaseConfig {

    @Autowired
    private Environment env;

    @Bean
    public DataSource dataSource()  {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();

            dataSource.setDriverClassName(env.getProperty("db.driver"));
            dataSource.setUrl(env.getProperty("db.url"));
            dataSource.setUsername(env.getProperty("db.username"));
            dataSource.setPassword(env.getProperty("db.password"));

        return dataSource;
    }

    @Bean
    public SpringLiquibase liquibase()  {
        SpringLiquibase liquibase = new SpringLiquibase();

        liquibase.setDataSource(dataSource());
        liquibase.setChangeLog("classpath:liquibase/db-changelog.xml");

        return liquibase;
    }
}