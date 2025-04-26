package com.example.ipl.tracker.service;

import com.example.ipl.tracker.dto.MatchDto;
import com.example.ipl.tracker.model.Match;
import com.example.ipl.tracker.respository.MatchRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ScoreService {

    private final CricbuzzApiService cricbuzzApiService;
    private final MatchRepository matchRepository;
    
    public Flux<MatchDto> getLiveMatches() {
        return cricbuzzApiService.getLiveMatches()
                .flatMapMany(Flux::fromIterable);
    }
    
    public Mono<MatchDto> getMatchDetails(String matchId) {
        return cricbuzzApiService.getMatchDetails(matchId);
    }
    
    // Update scores periodically from the API
    @Scheduled(fixedRate = 30000) // Update every 30 seconds
    public void updateLiveScores() {
        log.info("Updating live scores...");
        cricbuzzApiService.getLiveMatches()
                .subscribe(
                    matches -> {
                        // Update database with latest match data
                        log.info("Received {} live matches", matches.size());
                        // Process and save matches
                    },
                    error -> log.error("Error updating live scores: {}", error.getMessage())
                );
    }
    
    // Filter IPL matches from all live matches
    public List<Match> getIplMatches() {
        // Implement filtering logic for IPL matches
        return matchRepository.findBySeriesNameContainingIgnoreCase("Indian Premier League");
    }
}
