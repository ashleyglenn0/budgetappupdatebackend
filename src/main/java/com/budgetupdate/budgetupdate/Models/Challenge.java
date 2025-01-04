package com.budgetupdate.budgetupdate.Models;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="challenges")
public class Challenge {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Ensure this annotation is present
    @Column(name="challenge_id")
    private Long id;

    @Column(name="name", nullable = false)
    private String name;

    @Column(name="description", columnDefinition = "TEXT", nullable = false)
    private String description;

    @Column(name="start_date", nullable = false)
    private LocalDate startDate;

    @Column(name="end_date", nullable = false)
    private LocalDate endDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "challenge_status", nullable = false)
    private ChallengeStatus status;

    @Column(name="points", nullable = false)
    private int points;

    @Column(name = "progress", nullable = false)
    private double progress;

    @Column(name = "is_global")
    private boolean isGlobal; // Distinguishes global challenges

    @ManyToMany(mappedBy = "challenges")
    private List<User> users;

    @OneToMany(mappedBy = "challenge", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<UserChallengeProgress> userProgress = new ArrayList<>();

    public Challenge() {
        this.name = "";
        this.description = "";
        this.startDate = LocalDate.now();
        this.endDate = this.startDate.plusMonths(6);
        this.status = ChallengeStatus.ACTIVE;
        this.points = 0;
        this.progress = 0.0;
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

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public ChallengeStatus getStatus() {
        return status;
    }

    public void setStatus(ChallengeStatus status) {
        this.status = status;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public double getProgress() {
        return progress;
    }

    public void setProgress(double progress) {
        this.progress = progress;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public boolean isGlobal() {
        return isGlobal;
    }

    public void setGlobal(boolean global) {
        isGlobal = global;
    }
}
