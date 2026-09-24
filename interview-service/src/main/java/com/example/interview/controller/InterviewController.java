package com.example.interview.controller;

import com.example.interview.dto.ApiResponse;
import com.example.interview.entity.InterviewSchedule;
import com.example.interview.service.InterviewService;
import jakarta.validation.Valid;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/interviews")
public class InterviewController {

    private static final Logger log = LoggerFactory.getLogger(InterviewController.class);
    private final InterviewService interviewService;

    public InterviewController(InterviewService interviewService) {
        this.interviewService = interviewService;
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<InterviewSchedule>>> findAll() {
        return ResponseEntity.ok(
                ApiResponse.success("Interviews fetched", interviewService.findAll()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<InterviewSchedule>> findById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(
                ApiResponse.success("Interview fetched", interviewService.findById(id)));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<InterviewSchedule>> create(
            @Valid @RequestBody InterviewSchedule interview) {
        log.info("Received interview creation request");
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success(
                        "Interview scheduled", interviewService.create(interview)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<InterviewSchedule>> update(
            @PathVariable("id") Long id,
            @Valid @RequestBody InterviewSchedule interview) {
        return ResponseEntity.ok(
                ApiResponse.success("Interview updated", interviewService.update(id, interview)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> cancel(@PathVariable("id") Long id) {
        interviewService.cancel(id);
        return ResponseEntity.noContent().build();
    }
}
