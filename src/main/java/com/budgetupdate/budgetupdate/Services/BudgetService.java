package com.budgetupdate.budgetupdate.Services;

import com.budgetupdate.budgetupdate.Exceptions.ResourceNotFoundException;
import com.budgetupdate.budgetupdate.Models.Budget;
import com.budgetupdate.budgetupdate.Repositories.BudgetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class BudgetService {

    @Autowired
    private BudgetRepository budgetRepository;

    public Budget getMostRecentBudget(Long userId) {
        return budgetRepository.findTopByUserIdOrderByDateCreatedDesc(userId);
    }

    public List<String> getUpcomingPayments(Long budgetId) {
        Budget budget = budgetRepository.findById(budgetId)
                .orElseThrow(() -> new ResourceNotFoundException("Budget not found"));
        return budget.getUpcomingPayments(); // Returns a list of payment amounts
    }

    public BigDecimal getUpcomingIncome(Long budgetId) {
        Budget budget = budgetRepository.findById(budgetId)
                .orElseThrow(() -> new ResourceNotFoundException("Budget not found"));
        return budget.getUpcomingIncome();
    }
}
