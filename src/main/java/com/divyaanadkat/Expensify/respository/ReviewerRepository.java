package com.divyaanadkat.Expensify.respository;

import com.divyaanadkat.Expensify.entity.Reviewer;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ReviewerRepository extends CrudRepository<Reviewer, Integer> {
    Optional<Reviewer> findByUsername(String username);
}
