package com.example.candidate.repository;

import com.example.candidate.entity.Candidate;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CandidateRepository extends JpaRepository<Candidate, Long> {

    List<Candidate> findByDeletedFalse();

    Optional<Candidate> findByIdAndDeletedFalse(Long id);
}
