package com.budgetupdate.budgetupdate.Models;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "budgets")
public class Budget {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Ensure this annotation is present
    @Column(name="budget_id")
    private Long id;

    @Column(name="name", nullable = false)
    private String name;

    @Column(name="description", nullable = false, columnDefinition = "TEXT")
    private String description;

    @Column(name="date_created", nullable = false)
    private LocalDateTime dateCreated;

    @OneToMany(mappedBy="budget", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<BudgetItem> budgetItems = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name="user_id", referencedColumnName = "user_id", nullable = false)
    private User user;

    public Budget() {
        this.name = "";
        this.description = "";
        this.dateCreated = LocalDateTime.now();
        this.budgetItems = new ArrayList<>();
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(LocalDateTime date_created) {
        this.dateCreated = date_created;
    }

    public List<BudgetItem> getBudgetItems() {
        return budgetItems;
    }

    public void setBudgetItems(List<BudgetItem> budget_items) {
        this.budgetItems = budget_items;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<String> getUpcomingPayments() {
        List<String> payments = new ArrayList<>();
        for (BudgetItem item : budgetItems) {
            if (item.getBudgetItemType() == BudgetItemType.EXPENSE && item.getDueDate().isAfter(LocalDateTime.now())) {
                payments.add(item.getName() + " due on " + item.getDueDate());
            }
        }
        return payments.isEmpty() ? List.of("None") : payments;
    }

    public BigDecimal getUpcomingIncome() {
        return budgetItems.stream()
                .filter(item -> item.getBudgetItemType() == BudgetItemType.INCOME && item.getDueDate().isAfter(LocalDateTime.now()))
                .map(BudgetItem::getAmount) // Assuming BudgetItem::getAmount returns BigDecimal
                .filter(amount -> amount != null) // Ensure no null values are processed
                .reduce(BigDecimal.ZERO, BigDecimal::add); // Use BigDecimal::add to sum the values
    }
}
