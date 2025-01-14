package com.divyaanadkat.Expensify.controller;

import com.divyaanadkat.Expensify.entity.Employee;
import com.divyaanadkat.Expensify.entity.Expense;
import com.divyaanadkat.Expensify.entity.ExpensifyUser;
import com.divyaanadkat.Expensify.entity.Status;
import com.divyaanadkat.Expensify.service.EmployeeService;
import com.divyaanadkat.Expensify.service.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private ExpenseService expenseService;

    @GetMapping("/employees")
    public Iterable<Employee> findAllEmployees() {
        return this.employeeService.findAll();
    }

    @GetMapping("/employees/{employee_id}/expenses")
    public Iterable<Expense> findExpensesByEmployeeId(
            @PathVariable(value = "employee_id") Integer employeeId,
            @AuthenticationPrincipal ExpensifyUser expensifyUser
    ) {
        // Validate Employee id Authorization
        if (!expensifyUser.getId().equals(employeeId)) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        }

        return this.employeeService
                .findById(employeeId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND))
                .getExpenses();
    }

    @PostMapping("/employees/{employee_id}/expenses")
    public Expense addOneExpense(
            @PathVariable(value = "employee_id") Integer employeeId,
            @RequestBody Expense expense,
            @AuthenticationPrincipal ExpensifyUser expensifyUser
    ) {
        // Validate Employee id Authorization
        if (!expensifyUser.getId().equals(employeeId)) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        }

        Employee employee = this.employeeService.findById(employeeId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        return this.expenseService.addOne(employee, expense);
    }

    @DeleteMapping("/employees/{employee_id}/expenses/{expense_id}")
    public ResponseEntity<Void> deleteOneExpense(
            @PathVariable(value = "employee_id") Integer employeeId,
            @PathVariable(value = "expense_id") Integer expenseId,
            @AuthenticationPrincipal ExpensifyUser expensifyUser
    ) {
        // Validate Employee id Authorization
        if (!expensifyUser.getId().equals(employeeId)) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        }

        Employee employee = this.employeeService.findById(employeeId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        Expense expense = this.expenseService.findById(expenseId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        this.expenseService.deleteOne(employee, expense);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/expenses/{expense_id}/status")
    public ResponseEntity<Void> updateExpenseStatus(@PathVariable(value = "expense_id") Integer expenseId, @RequestBody Status status) {
        Expense expense = this.expenseService.findById(expenseId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        expense.getStatus().changeTo(status);
        expenseService.save(expense);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}





