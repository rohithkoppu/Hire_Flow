package com.example.candidate.service;

import com.example.candidate.entity.Candidate;
import com.example.candidate.exception.NotFoundException;
import com.example.candidate.repository.CandidateRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CandidateService {

    private static final Logger log = LoggerFactory.getLogger(CandidateService.class);
    private final CandidateRepository candidateRepository;

    public CandidateService(CandidateRepository candidateRepository) {
        this.candidateRepository = candidateRepository;
    }

    @Transactional(readOnly = true)
    public List<Candidate> findAll() {
        log.info("Fetching active candidates");
        return candidateRepository.findByDeletedFalse();
    }

    @Transactional(readOnly = true)
    public Candidate findById(Long id) {
        return candidateRepository.findByIdAndDeletedFalse(id)
                .orElseThrow(() -> new NotFoundException("Candidate not found: " + id));
    }

    @Transactional
    public Candidate create(Candidate candidate) {
        candidate.setId(null);
        log.info("Creating candidate {}", candidate.getName());
        return candidateRepository.save(candidate);
    }

    @Transactional
    public Candidate update(Long id, Candidate request) {
        Candidate candidate = findById(id);
        candidate.setName(request.getName());
        candidate.setSkills(request.getSkills());
        candidate.setExperience(request.getExperience());
        candidate.setQualification(request.getQualification());
        candidate.setDesignation(request.getDesignation());
        candidate.setNoticePeriod(request.getNoticePeriod());
        candidate.setLocation(request.getLocation());
        return candidateRepository.save(candidate);
    }

    @Transactional
    public void softDelete(Long id) {
        Candidate candidate = findById(id);
        candidate.setDeleted(true);
        candidateRepository.save(candidate);
        log.info("Soft deleted candidate {}", id);
    }
}
