package com.budgetupdate.budgetupdate.Models;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "global_challenges")
public class GlobalChallenge {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "global_challenge_id")
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description", columnDefinition = "TEXT", nullable = false)
    private String description;

    @Column(name = "start_date", nullable = false)
    private LocalDate startDate;

    @Column(name = "end_date", nullable = false)
    private LocalDate endDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "global_challenge_status", nullable = false)
    private ChallengeStatus status;

    @Column(name = "is_mandatory", nullable = false) // Updated field name for consistency
    private boolean isMandatory;

    @Column(name = "points", nullable = false)
    private int points;

    @Column(name = "progress")
    private double progress;

    @OneToMany(mappedBy = "globalChallenge", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<UserChallengeProgress> userProgress = new ArrayList<>();

    @ManyToMany
    @JoinTable(
            name = "user_global_challenge", // Join table name
            joinColumns = @JoinColumn(name = "global_challenge_id"), // Join column for GlobalChallenge
            inverseJoinColumns = @JoinColumn(name = "user_id") // Join column for User
    )
    private Set<User> users = new HashSet<>(); // Use Set to avoid duplicates

    // Getters and setters
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

    public boolean isMandatory() {
        return isMandatory;
    }

    public void setMandatory(boolean mandatory) {
        isMandatory = mandatory;
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

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }
}
