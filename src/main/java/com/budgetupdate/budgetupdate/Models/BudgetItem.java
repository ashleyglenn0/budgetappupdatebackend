package com.budgetupdate.budgetupdate.Models;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name="budget_items")
public class BudgetItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Ensure this annotation is present
    @Column(name="budget_item_id")
    private Long id;

    @Column(name="name", nullable = false)
    private String name;

    @Column(name="amount", nullable = false, precision = 10, scale = 2)
    private BigDecimal amount;

    @Enumerated(EnumType.STRING)
    @Column(name="budget_item_type", nullable = false)
    private BudgetItemType budgetItemType;

    @Enumerated(EnumType.STRING)
    @Column(name="category", nullable = false)
    private BudgetItemCategory category;

    @ManyToOne
    @JoinColumn(name = "budget_id", referencedColumnName = "budget_id", nullable = false)
    private Budget budget;

    public BudgetItem() {
        this.name = "";
        this.amount = new BigDecimal("0.00");
        this.budgetItemType = BudgetItemType.INCOME;
        this.category = BudgetItemCategory.MISC;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BudgetItemType getBudgetItemType() {
        return budgetItemType;
    }

    public void setBudgetItemType(BudgetItemType budgetItemType) {
        this.budgetItemType = budgetItemType;
    }

    public BudgetItemCategory getCategory() {
        return category;
    }

    public void setCategory(BudgetItemCategory category) {
        this.category = category;
    }

    public Budget getBudget() {
        return budget;
    }

    public void setBudget(Budget budget) {
        this.budget = budget;
    }
}
