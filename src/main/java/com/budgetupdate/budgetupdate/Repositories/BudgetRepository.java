package com.budgetupdate.budgetupdate.Repositories;

import com.budgetupdate.budgetupdate.Models.Budget;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BudgetRepository extends JpaRepository<Budget, Long> {
    Budget findTopByUserIdOrderByDateCreatedDesc(Long userId);
}
