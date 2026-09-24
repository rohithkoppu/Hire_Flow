package com.example.interview.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

@Entity
public class InterviewSchedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private Long candidateId;

    @NotNull
    private LocalDateTime interviewTime;

    @Min(1)
    @Max(5)
    private Integer techRating;

    @Min(1)
    @Max(5)
    private Integer hrRating;

    @Enumerated(EnumType.STRING)
    private InterviewStatus finalStatus = InterviewStatus.SCHEDULED;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCandidateId() {
        return candidateId;
    }

    public void setCandidateId(Long candidateId) {
        this.candidateId = candidateId;
    }

    public LocalDateTime getInterviewTime() {
        return interviewTime;
    }

    public void setInterviewTime(LocalDateTime interviewTime) {
        this.interviewTime = interviewTime;
    }

    public Integer getTechRating() {
        return techRating;
    }

    public void setTechRating(Integer techRating) {
        this.techRating = techRating;
    }

    public Integer getHrRating() {
        return hrRating;
    }

    public void setHrRating(Integer hrRating) {
        this.hrRating = hrRating;
    }

    public InterviewStatus getFinalStatus() {
        return finalStatus;
    }

    public void setFinalStatus(InterviewStatus finalStatus) {
        this.finalStatus = finalStatus;
    }
}