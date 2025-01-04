package com.budgetupdate.budgetupdate.Services;

import com.budgetupdate.budgetupdate.DTOs.ChallengeDTO;
import com.budgetupdate.budgetupdate.DTOs.DashboardDTO;
import com.budgetupdate.budgetupdate.DTOs.GlobalChallengeDTO;
import com.budgetupdate.budgetupdate.Models.Budget;
import com.budgetupdate.budgetupdate.Models.LetterStatus;
import com.budgetupdate.budgetupdate.Models.User;
import com.budgetupdate.budgetupdate.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class DashboardService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BudgetService budgetService;

    @Autowired
    private ChallengeService challengeService;

    @Autowired
    private LetterService letterService;

    public DashboardDTO getDashboardDataForUser(Long userId) {
        // Fetch user details
        User user = userRepository.findById(userId).orElseThrow(() ->
                new IllegalArgumentException("User not found"));

        // Get the latest budget for the user
        Budget latestBudget = budgetService.getMostRecentBudget(userId);
        List<String> upcomingPayments = latestBudget != null
                ? latestBudget.getUpcomingPayments()
                : List.of("None");
        BigDecimal upcomingIncome = latestBudget != null ? latestBudget.getUpcomingIncome() : BigDecimal.valueOf(0.0);

        // Get the latest letter status
        LetterStatus letterStatus = letterService.getLatestLetterStatus(userId);
        if (letterStatus == null) {
            letterStatus = LetterStatus.NOT_YET_SENT;
        }

        // Get challenges for the user
        List<ChallengeDTO> challenges = challengeService.getChallengesForUser(userId);

        // Get the latest active global challenge from ChallengeService
        GlobalChallengeDTO globalChallenge = challengeService.getLatestGlobalChallenge();

        // Map data to DashboardDTO
        DashboardDTO dashboardDTO = new DashboardDTO();
        dashboardDTO.setFirstName(user.getFirstName());
        dashboardDTO.setStatus(user.getStatus());
        dashboardDTO.setPoints(user.getPoints());
        dashboardDTO.setUpcomingPayments(upcomingPayments);
        dashboardDTO.setUpcomingIncome(upcomingIncome);
        dashboardDTO.setLetterStatus(letterStatus);
        dashboardDTO.setChallenges(challenges);
        dashboardDTO.setGlobalChallenge(globalChallenge);

        return dashboardDTO;
    }



}
