package com.example.ipl.tracker.service;

import com.example.ipl.tracker.dto.MatchDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
public class CricbuzzApiService {

    private final WebClient webClient;
    
    public Mono<List<MatchDto>> getLiveMatches() {
        return webClient.get()
                .retrieve()
                .bodyToMono(Map.class)
                .map(response -> {
                    // Parse the response from Cricbuzz API
                    // This is a simplified example - you'll need to adapt based on the actual API response structure
                    log.info("API Response: {}", response);
                    
                    // Extract matches data and convert to MatchDto objects
                    // Implementation depends on the actual structure of the API response
                    
                    return List.of(); // Replace with actual parsing logic
                })
                .doOnError(error -> log.error("Error fetching live matches: {}", error.getMessage()));
    }
    
    public Mono<MatchDto> getMatchDetails(String matchId) {
        return webClient.get()
                .uri("/matches/v1/" + matchId)
                .retrieve()
                .bodyToMono(Map.class)
                .map(response -> {
                    // Parse match details
                    log.info("Match details response: {}", response);
                    
                    // Convert to MatchDto
                    return new MatchDto(); // Replace with actual implementation
                })
                .doOnError(error -> log.error("Error fetching match details: {}", error.getMessage()));
    }
}
