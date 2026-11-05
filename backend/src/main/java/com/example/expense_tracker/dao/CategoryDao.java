package com.example.expense_tracker.dao;

import com.example.expense_tracker.vao.Category;
import org.springframework.data.repository.CrudRepository;

public interface CategoryDao extends CrudRepository<Category, Integer> {
}
