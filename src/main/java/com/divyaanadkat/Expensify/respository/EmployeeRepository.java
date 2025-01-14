package com.divyaanadkat.Expensify.respository;

import com.divyaanadkat.Expensify.entity.Employee;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface EmployeeRepository extends CrudRepository<Employee, Integer> {
    Optional<Employee> findByUsername(String username);
}
