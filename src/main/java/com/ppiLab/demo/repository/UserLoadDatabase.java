package com.ppiLab.demo.repository;

import com.ppiLab.demo.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class UserLoadDatabase {
    private static final Logger log = LoggerFactory.getLogger(UserLoadDatabase.class);
    @Bean
    CommandLineRunner initDatabase(UserRepository repository) {
        return args -> {
            log.info("Preloading " + repository.save(new User("Steve Jobs", "steve@apple.com")));
            log.info("Preloading " + repository.save(new User("Bill gate", "bill@ms.com")));
            log.info("Preloading " + repository.save(new User("Elon Musk", "elon@tesla.com")));
        };
    }
}