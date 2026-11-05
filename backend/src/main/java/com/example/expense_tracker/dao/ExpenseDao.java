package com.example.expense_tracker.dao;

import com.example.expense_tracker.vao.Expense;
import org.springframework.data.repository.CrudRepository;

public interface ExpenseDao extends CrudRepository<Expense, Integer> {
}
