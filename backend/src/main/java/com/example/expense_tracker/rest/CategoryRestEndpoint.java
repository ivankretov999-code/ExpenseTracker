package com.example.expense_tracker.rest;

import com.example.expense_tracker.dao.CategoryDao;
import com.example.expense_tracker.vao.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/categories")
public class CategoryRestEndpoint {
    @Autowired
    CategoryDao dao;

    @GetMapping
    public Iterable<Category> getCategories() {
        return dao.findAll();
    }
}
