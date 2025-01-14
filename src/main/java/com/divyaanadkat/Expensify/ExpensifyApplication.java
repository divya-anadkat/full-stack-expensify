package com.divyaanadkat.Expensify;

import com.divyaanadkat.Expensify.entity.Employee;
import com.divyaanadkat.Expensify.entity.Expense;
import com.divyaanadkat.Expensify.entity.Reviewer;
import com.divyaanadkat.Expensify.respository.EmployeeRepository;
import com.divyaanadkat.Expensify.respository.ReviewerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;
import java.util.List;

@SpringBootApplication
public class ExpensifyApplication {

    public static void main(String[] args) {
        SpringApplication.run(ExpensifyApplication.class, args);
    }

    @Bean
    public CommandLineRunner initDatabase(
            EmployeeRepository employeeRepository,
            ReviewerRepository reviewerRepository,
            PasswordEncoder passwordEncoder) {
        return args -> {
            // Example Employees
            List<Employee> employees = List.of(
                    new Employee(
                            "employee_1",
                            passwordEncoder.encode("password"),
                            List.of(
                                    Expense.builder()
                                            .merchant("Amazon")
                                            .description("Purchase of Presenter")
                                            .purchaseDate(LocalDate.now())
                                            .amount(600)
                                            .build()
                            )
                    ),
                    new Employee("employee_2", passwordEncoder.encode("password")),
                    new Employee("employee_3", passwordEncoder.encode("password"))
            );

            // Example Reviewers
            List<Reviewer> reviewers = List.of(
                    new Reviewer("reviewer_1", passwordEncoder.encode("password")),
                    new Reviewer("reviewer_2", passwordEncoder.encode("password"))
            );
            employees.forEach(employeeRepository::save);
            reviewers.forEach(reviewerRepository::save);
        };
    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
