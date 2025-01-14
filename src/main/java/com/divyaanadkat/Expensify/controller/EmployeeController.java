package com.divyaanadkat.Expensify.controller;

import com.divyaanadkat.Expensify.entity.Employee;
import com.divyaanadkat.Expensify.entity.Expense;
import com.divyaanadkat.Expensify.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/employees")
    public Iterable<Employee> findAllEmployees() {
        return this.employeeService.findAll();
    }

    @GetMapping("/employees/{employee_id}/expenses")
    public Iterable<Expense> findExpensesByEmployeeId(@PathVariable(value = "employee_id") Integer employeeId) {
        return this.employeeService
                .findById(employeeId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND))
                .getExpenses();
    }
}
