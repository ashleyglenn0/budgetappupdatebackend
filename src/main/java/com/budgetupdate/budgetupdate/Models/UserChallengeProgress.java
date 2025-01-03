package com.budgetupdate.budgetupdate.Models;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "user_challenge_progress")
public class UserChallengeProgress {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_challenge_progress_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id", nullable = false) // Map to User's primary key
    private User user;

    @ManyToOne
    @JoinColumn(name = "challenge_id", referencedColumnName = "challenge_id", nullable = false) // Map to Challenge's primary key
    private Challenge challenge;

    @ManyToOne
    @JoinColumn(name = "global_challenge_id", referencedColumnName = "global_challenge_id" , nullable = false)
    private GlobalChallenge globalChallenge;

    @Column(name = "progress", nullable = false)
    private double progress; // Progress percentage (0.0 to 100.0)

    @Column(name = "is_complete", nullable = false)
    private boolean isCompleted;

    @Column(name = "date_completed")
    private LocalDate dateCompleted;

    public UserChallengeProgress() {
        this.progress = 0.0;
        this.isCompleted = false;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Challenge getChallenge() {
        return challenge;
    }

    public void setChallenge(Challenge challenge) {
        this.challenge = challenge;
    }

    public double getProgress() {
        return progress;
    }

    public void setProgress(double progress) {
        this.progress = progress;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void setCompleted(boolean completed) {
        isCompleted = completed;
    }

    public LocalDate getDateCompleted() {
        return dateCompleted;
    }

    public void setDateCompleted(LocalDate dateCompleted) {
        this.dateCompleted = dateCompleted;
    }
}
