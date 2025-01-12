package com.divyaanadkat.Expensify.entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "statuses")
public class Status {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private State state;

    private String reviewedBy;

    private LocalDate reviewDate;

    private String comment;

    public enum State {
        IN_REVIEW,
        APPROVED,
        REJECTED
    }
}
