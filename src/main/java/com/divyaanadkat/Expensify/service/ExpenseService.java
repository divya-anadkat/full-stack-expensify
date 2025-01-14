package com.divyaanadkat.Expensify.service;

import com.divyaanadkat.Expensify.entity.Employee;
import com.divyaanadkat.Expensify.entity.Expense;
import com.divyaanadkat.Expensify.respository.EmployeeRepository;
import com.divyaanadkat.Expensify.respository.ExpenseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ExpenseService {
    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private ExpenseRepository expenseRepository;

    public Expense addOne(Employee employee, Expense expense) {
        Expense expenseWithId = expenseRepository.save(expense);
        employee.getExpenses().add(expenseWithId);
        employeeRepository.save(employee);
        return expenseWithId;
    }

    public Optional<Expense> findById(Integer id) {
        return this.expenseRepository.findById(id);
    }

    public void deleteOne(Employee employee, Expense expense) {
        employee.getExpenses().remove(expense);
        employeeRepository.save(employee);
    }

    public void save(Expense expense) {
        this.expenseRepository.save(expense);
    }
}
