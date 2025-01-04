package com.budgetupdate.budgetupdate.Repositories;

import com.budgetupdate.budgetupdate.Models.CreditRepairLetter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LetterRepository extends JpaRepository<CreditRepairLetter, Long> {
    CreditRepairLetter findTopByUserIdOrderByDateSentDesc(Long userId);
}
