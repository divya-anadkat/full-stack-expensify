package com.divyaanadkat.Expensify.respository;

import com.divyaanadkat.Expensify.entity.Expense;
import org.springframework.data.repository.CrudRepository;

public interface ExpenseRepository extends CrudRepository<Expense, Integer> {}
