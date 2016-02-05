package com.job.batch.data.repository;

import com.job.batch.data.ITContext;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

@ContextConfiguration(classes = ITContext.class)
public class ITTestBase extends AbstractTestNGSpringContextTests {
    private EmbeddedDatabase db;

    @BeforeClass
    public void setUp() {

        db = new EmbeddedDatabaseBuilder()
                .setType(EmbeddedDatabaseType.H2)
                .addScript("classpath:drop-db.sql")
                .addScript("classpath:create-db.sql")
                .addScript("classpath:insert-db.sql")
                .build();
    }

    @AfterClass
    public void tearDown() {
        db = new EmbeddedDatabaseBuilder()
                .setType(EmbeddedDatabaseType.H2)
                .addScript("classpath:drop-db.sql")
                .build();
        db.shutdown();
    }
}
