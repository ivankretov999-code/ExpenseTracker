package com.example.expense_tracker.vao;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Expense {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String naziv;
    private double znesek;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category kategorija;

    public Expense() {}
}
