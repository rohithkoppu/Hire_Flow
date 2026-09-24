package com.example.candidate.controller;

import com.example.candidate.dto.ApiResponse;
import com.example.candidate.entity.Candidate;
import com.example.candidate.service.CandidateService;
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
@RequestMapping("/api/candidates")
public class CandidateController {

    private static final Logger log = LoggerFactory.getLogger(CandidateController.class);
    private final CandidateService candidateService;

    public CandidateController(CandidateService candidateService) {
        this.candidateService = candidateService;
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<Candidate>>> findAll() {
        return ResponseEntity.ok(
                ApiResponse.success("Candidates fetched", candidateService.findAll()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Candidate>> findById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(
                ApiResponse.success("Candidate fetched", candidateService.findById(id)));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Candidate>> create(
            @Valid @RequestBody Candidate candidate) {
        log.info("Received candidate creation request");
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success("Candidate created", candidateService.create(candidate)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Candidate>> update(
            @PathVariable("id") Long id,
            @Valid @RequestBody Candidate candidate) {
        return ResponseEntity.ok(
                ApiResponse.success("Candidate updated", candidateService.update(id, candidate)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        candidateService.softDelete(id);
        return ResponseEntity.noContent().build();
    }
}
