package com.example.expense_tracker;

import com.example.expense_tracker.dao.CategoryDao;
import com.example.expense_tracker.vao.Category;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ExpenseTrackerApplication {
    public static void main(String[] args) {
        SpringApplication.run(ExpenseTrackerApplication.class, args);
    }

    @Bean
    CommandLineRunner pripraviKategorije(CategoryDao dao) {
        return args -> {
            if (dao.count() == 0) {
                dao.save(new Category("Prevoz"));
                dao.save(new Category("Hrana"));
                dao.save(new Category("Ostalo"));
            }
        };
    }
}
