package com.example.expense_tracker.rest;

import com.example.expense_tracker.dao.*;
import com.example.expense_tracker.vao.*;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/expenses")
public class ExpenseRestEndpoint {
    @Autowired
    ExpenseDao dao;

    @Autowired
    CategoryDao categoryDao;

    @Operation(summary = "Pridobi vse stroske")
    @GetMapping
    public Iterable<Expense> getExpenses() {
        return dao.findAll();
    }

    @Operation(summary = "Pridobi en strosek")
    @GetMapping("/{id}")
    public ResponseEntity<Expense> getExpense(@PathVariable int id) {
        return dao.findById(id).map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Dodaj strosek")
    @PostMapping
    public ResponseEntity<Expense> addExpense(@RequestBody Expense expense) {
        if (!setCategory(expense)) return ResponseEntity.badRequest().build();
        expense.setId(0); // ID ustvari podatkovna baza
        return ResponseEntity.ok(dao.save(expense));
    }

    @Operation(summary = "Spremeni strosek")
    @PutMapping("/{id}")
    public ResponseEntity<Expense> updateExpense(@PathVariable int id, @RequestBody Expense podatki) {
        var existing = dao.findById(id);
        if (existing.isEmpty()) return ResponseEntity.notFound().build();
        if (!setCategory(podatki)) return ResponseEntity.badRequest().build();
        Expense expense = existing.get();
        expense.setNaziv(podatki.getNaziv());
        expense.setZnesek(podatki.getZnesek());
        expense.setKategorija(podatki.getKategorija());
        return ResponseEntity.ok(dao.save(expense));
    }

    @Operation(summary = "Izbrisi strosek")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteExpense(@PathVariable int id) {
        if (!dao.existsById(id)) return ResponseEntity.notFound().build();
        dao.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    private boolean setCategory(Expense expense) {
        if (expense.getKategorija() == null) return false;
        var category = categoryDao.findById(expense.getKategorija().getId());
        if (category.isEmpty()) return false;
        expense.setKategorija(category.get());
        return true;
    }
}
