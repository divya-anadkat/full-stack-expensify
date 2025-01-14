package com.divyaanadkat.Expensify.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "employees")
public class Employee extends ExpensifyUser {
    @OneToMany(
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER,
            orphanRemoval = true
    )
    private List<Expense> expenses;

    private Employee() {}

    public Employee(String username, String password) {
        this(username, password, new ArrayList<>());
    }

    public Employee(String username, String password, List<Expense> expenses) {
        super(username, password, Role.EMPLOYEE);
        this.expenses = expenses;
    }

    public List<Expense> getExpenses() {
        return expenses;
    }
}
