package com.example.interview.client;

import com.example.interview.config.FeignConfig;
import java.util.Map;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(
        name = "candidate-service",
        configuration = FeignConfig.class
)
public interface CandidateClient {

    @GetMapping("/api/candidates/{id}")
    Map<String, Object> findCandidateById(@PathVariable("id") Long id);
}