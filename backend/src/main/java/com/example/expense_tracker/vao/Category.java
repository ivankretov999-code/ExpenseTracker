package com.example.expense_tracker.vao;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String naziv;

    public Category() {}
    public Category(String naziv) { this.naziv = naziv; }
}
