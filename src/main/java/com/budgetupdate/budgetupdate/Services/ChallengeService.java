package com.budgetupdate.budgetupdate.Services;

import com.budgetupdate.budgetupdate.DTOs.ChallengeDTO;
import com.budgetupdate.budgetupdate.DTOs.GlobalChallengeDTO;
import com.budgetupdate.budgetupdate.Models.Challenge;
import com.budgetupdate.budgetupdate.Models.ChallengeStatus;
import com.budgetupdate.budgetupdate.Models.User;
import com.budgetupdate.budgetupdate.Repositories.ChallengeRepository;
import com.budgetupdate.budgetupdate.Repositories.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChallengeService {

    @Autowired
    private ChallengeRepository challengeRepository;
    @Autowired
    private UserRepository userRepository;

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
                    dto.setEndDate(challenge.getEndDate());
                    dto.setStartDate(challenge.getStartDate());
                    return dto;
                })
                .toList();
    }

    public GlobalChallengeDTO getLatestGlobalChallenge() {
        ChallengeStatus activeStatus = ChallengeStatus.ACTIVE;
        return challengeRepository.findTopByIsGlobalAndStatusOrderByStartDateDesc(true, activeStatus)
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

    @Transactional
    public void enrollUserInChallenge(Long challengeId, Long userId) {
        // Fetch the user by ID
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with ID: " + userId));

        // Fetch the challenge by ID
        Challenge challenge = challengeRepository.findById(challengeId)
                .orElseThrow(() -> new EntityNotFoundException("Challenge not found with ID: " + challengeId));

        // Check if the user is already enrolled in the challenge
        if (user.getChallenges().contains(challenge)) {
            throw new IllegalStateException("User is already enrolled in this challenge.");
        }

        // Add the challenge to the user's challenges
        user.getChallenges().add(challenge);

        // Save the user to persist the relationship
        userRepository.save(user);
    }

    public List<ChallengeDTO> getAllChallengesForUser(Long userId) {
        // Fetch challenges not yet enrolled by the user
        List<Challenge> challenges = challengeRepository.findAvailableChallengesForUser(userId);

        return challenges.stream()
                .map(challenge -> new ChallengeDTO(
                        challenge.getId(),
                        challenge.getName(),
                        challenge.getDescription(),
                        challenge.getPoints(),
                        challenge.isGlobal(),
                        challenge.getStartDate(), // Add startDate
                        challenge.getEndDate()    // Add endDate
                ))
                .toList();
    }


}
