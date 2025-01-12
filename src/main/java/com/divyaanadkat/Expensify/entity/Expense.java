package com.divyaanadkat.Expensify.entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "expenses")
public class Expense {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String merchant;
    private String description;
    private LocalDate purchaseDate;
    private double amount;

    @OneToOne(cascade = CascadeType.ALL)
    private Status status;

    // For Hibernate
    private Expense() {}

    // For Builder
    private Expense(Builder builder){
        this.merchant = builder.merchant;
        this.description = builder.description;
        this.purchaseDate = builder.purchaseDate;
        this.amount = builder.amount;
        this.status = Status.builder().state(Status.State.IN_REVIEW).build();
    }

    // Expose Builder
    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String merchant;
        private String description;
        private LocalDate purchaseDate;
        private double amount;

        public Builder merchant(String merchant) {
            this.merchant = merchant;
            return this;
        }
        public Builder description(String description) {
            this.description = description;
            return this;
        }
        public Builder purchaseDate(LocalDate purchaseDate){
            this.purchaseDate = purchaseDate;
            return this;
        }
        public Builder amount(double amount) {
            this.amount = amount;
            return this;
        }
        public Expense build(){
            return new Expense(this);
        }
    }
}
