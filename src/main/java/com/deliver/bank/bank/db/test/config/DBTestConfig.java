package com.deliver.bank.bank.db.test.config;

import com.deliver.bank.bank.db.test.service.DBTestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.text.ParseException;

@Configuration
@Profile("test")
public class DBTestConfig {

    private DBTestService dbService;

    @Autowired
    public DBTestConfig(DBTestService dbService) {
        this.dbService = dbService;
    }

    @Bean
    public boolean instantiateDatabase() throws ParseException {
        this.dbService.instantiateTestDatabase();
        return true;
    }
}
