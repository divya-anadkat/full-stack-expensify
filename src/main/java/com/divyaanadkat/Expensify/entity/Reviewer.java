package com.divyaanadkat.Expensify.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "reviewers")
public class Reviewer extends ExpensifyUser{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private Reviewer() {}

    public Reviewer(String username, String password) {
        super(username, password, Role.REVIEWER);
    }
}
