package com.otpp.jobBatch.data.config;


import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EntityScan(basePackages = {"com.otpp.jobBatch.data.entity"})
@EnableJpaRepositories(basePackages = "com.otpp.jobBatch.data.repository")
@PropertySource("classpath:datasource.properties")
@EnableTransactionManagement
public class DataContext {
    @Bean
    public EmbeddedDatabase DataSource(){
        return new EmbeddedDatabaseBuilder()
                .setType(EmbeddedDatabaseType.H2)
                .addScript("classpath:drop-db.sql")
                .addScript("classpath:create-db.sql")
                .addScript("classpath:insert-db.sql")
                .build();
    }


}
