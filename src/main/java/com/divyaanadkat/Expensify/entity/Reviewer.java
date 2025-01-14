package com.divyaanadkat.Expensify.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "reviewers")
public class Reviewer extends ExpensifyUser{
    private Reviewer() {}

    public Reviewer(String username, String password) {
        super(username, password, Role.ROLE_REVIEWER);
    }
}
