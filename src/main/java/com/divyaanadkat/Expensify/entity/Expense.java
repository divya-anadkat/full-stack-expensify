package com.divyaanadkat.Expensify.entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "expenses")
public class Expense {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String merchant;

    private String description;

    private LocalDate purchaseDate;

    private double amount;

    @OneToOne(cascade = CascadeType.ALL)
    private Status status;
}
