package com.budgetupdate.budgetupdate.DTOs;

import com.budgetupdate.budgetupdate.Models.LetterStatus;
import com.budgetupdate.budgetupdate.Models.UserStatus;

import java.math.BigDecimal;
import java.util.List;

public class DashboardDTO {
    private String firstName;
    private UserStatus status;
    private int points;
    private List<String> upcomingPayments;
    private BigDecimal upcomingIncome;
    private LetterStatus letterStatus;
    private List<ChallengeDTO> challenges;
    private GlobalChallengeDTO globalChallenge; // Single global challenge

    // Getters and Setters
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public UserStatus getStatus() {
        return status;
    }

    public void setStatus(UserStatus status) {
        this.status = status;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public List<String> getUpcomingPayments() {
        return upcomingPayments;
    }

    public void setUpcomingPayments(List<String> upcomingPayments) {
        this.upcomingPayments = upcomingPayments;
    }

    public BigDecimal getUpcomingIncome() {
        return upcomingIncome;
    }

    public void setUpcomingIncome(BigDecimal upcomingIncome) {
        this.upcomingIncome = upcomingIncome;
    }

    public LetterStatus getLetterStatus() {
        return letterStatus;
    }

    public void setLetterStatus(LetterStatus letterStatus) {
        this.letterStatus = letterStatus;
    }

    public List<ChallengeDTO> getChallenges() {
        return challenges;
    }

    public void setChallenges(List<ChallengeDTO> challenges) {
        this.challenges = challenges;
    }

    public GlobalChallengeDTO getGlobalChallenge() {
        return globalChallenge;
    }

    public void setGlobalChallenge(GlobalChallengeDTO globalChallenge) {
        this.globalChallenge = globalChallenge;
    }
}

