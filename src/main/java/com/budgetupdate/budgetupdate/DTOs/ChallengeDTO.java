package com.budgetupdate.budgetupdate.DTOs;

import java.time.LocalDate;

public class ChallengeDTO {
    private Long id;
    private String name;
    private String description;
    private int points;
    private double progress;
    private boolean isGlobal;
    private LocalDate startDate; // Include this
    private LocalDate endDate;   // Include this

    public ChallengeDTO(Long id, String name, String description, int points, boolean isGlobal, LocalDate startDate, LocalDate endDate) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.points = points;
        this.isGlobal = isGlobal;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public ChallengeDTO(){
        this.id = id;
        this.name = name;
        this.description = description;
        this.points = points;
        this.isGlobal = isGlobal;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    // Getters and Setters
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

    public boolean isGlobal() {
        return isGlobal;
    }

    public void setGlobal(boolean global) {
        isGlobal = global;
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
}

