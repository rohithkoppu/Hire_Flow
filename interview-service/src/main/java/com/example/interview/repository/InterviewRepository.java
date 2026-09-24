package com.example.interview.repository;

import com.example.interview.entity.InterviewSchedule;
import com.example.interview.entity.InterviewStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface InterviewRepository extends JpaRepository<InterviewSchedule, Long> {

    List<InterviewSchedule> findByFinalStatusNot(InterviewStatus finalStatus);

    Optional<InterviewSchedule> findByIdAndFinalStatusNot(Long id, InterviewStatus status);

}
