package com.divyaanadkat.Expensify;

import com.divyaanadkat.Expensify.entity.ExpensifyUser;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfiguration {
    @Autowired
    private ObjectMapper objectMapper;


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(requests -> requests
                        .requestMatchers(HttpMethod.GET, "/employees")
                        .hasRole(ExpensifyUser.Role.ROLE_REVIEWER.withoutRolePrefix())

                        .requestMatchers(HttpMethod.PUT, "/expenses/{expense_id}/status")
                        .hasRole(ExpensifyUser.Role.ROLE_REVIEWER.withoutRolePrefix())

                        .requestMatchers(HttpMethod.GET, "/employees/{employee_id}/expenses")
                        .hasRole(ExpensifyUser.Role.ROLE_EMPLOYEE.withoutRolePrefix())

                        .requestMatchers(HttpMethod.POST, "/employees/{employee_id}/expenses")
                        .hasRole(ExpensifyUser.Role.ROLE_EMPLOYEE.withoutRolePrefix())

                        .requestMatchers(HttpMethod.DELETE, "/employees/{employee_id}/expenses/{expense_id}")
                        .hasRole(ExpensifyUser.Role.ROLE_EMPLOYEE.withoutRolePrefix())

                        .anyRequest()
                        .authenticated()
                )
                .formLogin(formLogin -> formLogin
                        .successHandler((req, res, auth) -> {
                            res.setContentType("application/json");
                            res.getWriter().write(objectMapper
                                    .writerFor(ExpensifyUser.class)
                                    .writeValueAsString(auth.getPrincipal())
                            );
                        })
                        .failureHandler((req, res, ex) -> {
                            res.setStatus(HttpStatus.UNAUTHORIZED.value());
                        })
                );

        return http.build();
    }
}
