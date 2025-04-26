package com.example.ipl.tracker.controller;

import com.example.ipl.tracker.dto.MatchDto;
import com.example.ipl.tracker.service.ScoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/scores")
@RequiredArgsConstructor
public class ScoreController {

    private final ScoreService scoreService;
    
    @GetMapping(value = "/live", produces = MediaType.APPLICATION_JSON_VALUE)
    public Flux<MatchDto> getLiveMatches() {
        return scoreService.getLiveMatches();
    }
    
    @GetMapping(value = "/ipl", produces = MediaType.APPLICATION_JSON_VALUE)
    public Flux<MatchDto> getIplMatches() {
        // Convert List to Flux
        return Flux.fromIterable(scoreService.getIplMatches().stream()
                // Map to DTO - implement this conversion
                .map(match -> new MatchDto())
                .toList());
    }
    
    @GetMapping(value = "/match/{matchId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<MatchDto> getMatchDetails(@PathVariable String matchId) {
        return scoreService.getMatchDetails(matchId);
    }
}
