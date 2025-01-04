package com.budgetupdate.budgetupdate.Services;

import com.budgetupdate.budgetupdate.DTOs.ChallengeDTO;
import com.budgetupdate.budgetupdate.DTOs.GlobalChallengeDTO;
import com.budgetupdate.budgetupdate.Models.Challenge;
import com.budgetupdate.budgetupdate.Models.ChallengeStatus;
import com.budgetupdate.budgetupdate.Repositories.ChallengeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChallengeService {

    @Autowired
    private ChallengeRepository challengeRepository;

    public List<ChallengeDTO> getChallengesForUser(Long userId) {
        List<Challenge> challenges = challengeRepository.findByUsers_Id(userId);

        // Convert entities to DTOs
        return challenges.stream()
                .map(challenge -> {
                    ChallengeDTO dto = new ChallengeDTO();
                    dto.setId(challenge.getId());
                    dto.setName(challenge.getName());
                    dto.setDescription(challenge.getDescription());
                    dto.setPoints(challenge.getPoints());
                    dto.setProgress(challenge.getProgress());
                    dto.setGlobal(challenge.isGlobal());
                    return dto;
                })
                .toList();
    }

    public GlobalChallengeDTO getLatestGlobalChallenge() {
        ChallengeStatus activeStatus = ChallengeStatus.ACTIVE;
        return challengeRepository. findTopByIsGlobalAndStatusOrderByStartDateDesc(true, activeStatus)
                .map(challenge -> {
                    GlobalChallengeDTO dto = new GlobalChallengeDTO();
                    dto.setName(challenge.getName());
                    dto.setDescription(challenge.getDescription());
                    dto.setStartDate(challenge.getStartDate());
                    dto.setEndDate(challenge.getEndDate());
                    dto.setPoints(challenge.getPoints());
                    return dto;
                })
                .orElse(null);
    }


}
