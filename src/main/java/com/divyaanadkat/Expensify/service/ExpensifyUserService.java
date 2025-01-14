package com.divyaanadkat.Expensify.service;

import com.divyaanadkat.Expensify.entity.Employee;
import com.divyaanadkat.Expensify.entity.Reviewer;
import com.divyaanadkat.Expensify.respository.EmployeeRepository;
import com.divyaanadkat.Expensify.respository.ReviewerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ExpensifyUserService  implements UserDetailsService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private ReviewerRepository reviewerRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Employee> employee = employeeRepository.findByUsername(username);
        if(employee.isPresent()) {
            return employee.get();
        }

        Optional<Reviewer> reviewer = reviewerRepository.findByUsername(username);
        if (reviewer.isPresent()) {
            return reviewer.get();
        }

        throw new UsernameNotFoundException("User not found");
    }
}
