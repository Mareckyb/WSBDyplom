package com.example.dyplom.config;

import org.flywaydb.core.Flyway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class FlywayConfiguration {

    @Autowired
    public FlywayConfiguration(DataSource datasource) {
        Flyway.configure().baselineOnMigrate(true).dataSource(datasource).load().migrate();
    }
}
