package com.divyaanadkat.Expensify.entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "statuses")
public class Status {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Enumerated(value = EnumType.STRING)
    private State state;
    private String reviewedBy;
    private LocalDate reviewDate;
    private String comment;

    public enum State {
        IN_REVIEW,
        APPROVED,
        REJECTED
    }

    public Integer getId() {
        return id;
    }

    public State getState() {
        return state;
    }

    public String getReviewedBy() {
        return reviewedBy;
    }

    public LocalDate getReviewDate() {
        return reviewDate;
    }

    public String getComment() {
        return comment;
    }

    // For Hibernate
    private Status() {}

    // For Builder
    private Status(Builder builder) {
        this.state = builder.state;
        this.reviewedBy = builder.reviewedBy;
        this.reviewDate = builder.reviewDate;
        this.comment = builder.comment;
    }

    // Expose Builder
    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private State state;
        private String reviewedBy;
        private LocalDate reviewDate;
        private String comment;

        public Builder state(State state) {
            this.state = state;
            return this;
        }
        public Builder reviewedBy(String reviewedBy) {
            this.reviewedBy = reviewedBy;
            return this;
        }
        public Builder reviewDate(LocalDate reviewDate) {
            this.reviewDate = reviewDate;
            return this;
        }
        public Builder comment(String comment) {
            this.comment = comment;
            return this;
        }
        public Status build() {
            return new Status(this);
        }
    }
}
