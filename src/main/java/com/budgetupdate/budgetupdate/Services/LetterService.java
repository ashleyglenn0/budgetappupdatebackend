package com.budgetupdate.budgetupdate.Services;

import com.budgetupdate.budgetupdate.Models.CreditRepairLetter;
import com.budgetupdate.budgetupdate.Models.LetterStatus;
import com.budgetupdate.budgetupdate.Repositories.LetterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LetterService {

    @Autowired
    private LetterRepository letterRepository;

    public LetterStatus getLatestLetterStatus(Long userId) {
        CreditRepairLetter latestLetter = letterRepository.findTopByUserIdOrderByDateSentDesc(userId);
        return (latestLetter != null) ? latestLetter.getStatus() : null;
    }

}
