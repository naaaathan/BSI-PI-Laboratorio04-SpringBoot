package com.ppiLab.demo.repository;

import com.ppiLab.demo.model.Item;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Date;

@Configuration
public class ItemLoadDatabase {

    private static final Logger LOG = LoggerFactory.getLogger(ItemLoadDatabase.class);

    @Bean
    CommandLineRunner initDatabaseItem(ItemRepository repository) {

        return args -> {
            LOG.info("Preloading " + repository.save(new Item(10, 10, "Livro Infantil", "Livro de histórias ludicas", new Date(System.currentTimeMillis()))));
            LOG.info("Preloading " + repository.save(new Item(11, 1000, "Celular", "Smartphone", new Date(System.currentTimeMillis()))));
            LOG.info("Preloading " + repository.save(new Item(12, 5000, "Notebook", "Computador portatil", new Date(System.currentTimeMillis()))));
        };
    }

}
