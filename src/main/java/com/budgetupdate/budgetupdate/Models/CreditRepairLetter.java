package com.budgetupdate.budgetupdate.Models;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name="letters")
public class CreditRepairLetter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Ensure this annotation is present
    @Column(name="letter_id")
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name="letter_type", nullable = false)
    private CreditRepairLetterType creditRepairLetterType;

    @Column(name = "recipient", nullable = false)
    private String recipient;

    @Column(name = "date_sent", nullable = false)
    private LocalDate dateSent;

    @Column(name="content", columnDefinition = "TEXT", nullable = false)
    private String content;

    @Enumerated(EnumType.STRING)
    @Column(name = "letter_status", nullable = false)
    private LetterStatus status;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id", nullable = false)
    private User user;

    public CreditRepairLetter() {
        this.creditRepairLetterType = CreditRepairLetterType.VALIDATION;
        this.recipient = "";
        this.dateSent = LocalDate.now();
        this.content = "";
        this.status = LetterStatus.NOT_YET_SENT;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CreditRepairLetterType getCreditRepairLetterType() {
        return creditRepairLetterType;
    }

    public void setCreditRepairLetterType(CreditRepairLetterType creditRepairLetterType) {
        this.creditRepairLetterType = creditRepairLetterType;
    }

    public String getRecipient() {
        return recipient;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    public LocalDate getDateSent() {
        return dateSent;
    }

    public void setDateSent(LocalDate dateDent) {
        this.dateSent = dateSent;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LetterStatus getStatus() {
        return status;
    }

    public void setStatus(LetterStatus status) {
        this.status = status;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
