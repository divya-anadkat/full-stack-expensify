package com.divyaanadkat.Expensify.respository;

import com.divyaanadkat.Expensify.entity.Employee;
import org.springframework.data.repository.CrudRepository;

public interface EmployeeRepository extends CrudRepository<Employee, Integer> {}
