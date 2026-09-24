package com.example.interview.service;

import com.example.interview.client.CandidateClient;
import com.example.interview.entity.InterviewSchedule;
import com.example.interview.entity.InterviewStatus;
import com.example.interview.exception.NotFoundException;
import com.example.interview.repository.InterviewRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class InterviewService {

    private static final Logger log = LoggerFactory.getLogger(InterviewService.class);

    private final InterviewRepository interviewRepository;
    private final CandidateClient candidateClient;

    public InterviewService(
            InterviewRepository interviewRepository,
            CandidateClient candidateClient) {
        this.interviewRepository = interviewRepository;
        this.candidateClient = candidateClient;
    }

    @Transactional(readOnly = true)
    public List<InterviewSchedule> findAll() {
        return interviewRepository.findByFinalStatusNot(InterviewStatus.CANCELLED);
    }

    @Transactional(readOnly = true)
    public InterviewSchedule findById(Long id) {
        return interviewRepository.findByIdAndFinalStatusNot(id, InterviewStatus.CANCELLED)
                .orElseThrow(() -> new NotFoundException("Interview not found: " + id));
    }

    @Transactional
    public InterviewSchedule create(InterviewSchedule interview) {
        candidateClient.findCandidateById(interview.getCandidateId());
        interview.setId(null);
        log.info("Scheduling interview for candidate {}", interview.getCandidateId());
        return interviewRepository.save(interview);
    }

    @Transactional
    public InterviewSchedule update(Long id, InterviewSchedule request) {
        InterviewSchedule interview = findById(id);
        candidateClient.findCandidateById(request.getCandidateId());
        interview.setCandidateId(request.getCandidateId());
        interview.setInterviewTime(request.getInterviewTime());
        interview.setTechRating(request.getTechRating());
        interview.setHrRating(request.getHrRating());
        interview.setFinalStatus(request.getFinalStatus());
        return interviewRepository.save(interview);
    }

    @Transactional
    public void cancel(Long id) {
        InterviewSchedule interview = findById(id);
        interview.setFinalStatus(InterviewStatus.CANCELLED);
        interviewRepository.save(interview);
        log.info("Cancelled interview {}", id);
    }
}
