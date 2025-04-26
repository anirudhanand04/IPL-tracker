package com.example.ipl.tracker.service;

import com.example.ipl.tracker.dto.MatchDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
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
                    log.info("API Response: {}", response);
                    
                    List<MatchDto> matches = new ArrayList<>();
                    // Extract the matches from the response
                    try {
                        if (response.containsKey("typeMatches")) {
                            List<Map<String, Object>> typeMatches = (List<Map<String, Object>>) response.get("typeMatches");
                            
                            for (Map<String, Object> typeMatch : typeMatches) {
                                if (typeMatch.containsKey("seriesMatches")) {
                                    List<Map<String, Object>> seriesMatches = (List<Map<String, Object>>) typeMatch.get("seriesMatches");
                                    
                                    for (Map<String, Object> seriesMatch : seriesMatches) {
                                        if (seriesMatch.containsKey("seriesAdWrapper") && 
                                            ((Map)seriesMatch.get("seriesAdWrapper")).containsKey("matches")) {
                                            
                                            Map<String, Object> seriesAdWrapper = (Map<String, Object>) seriesMatch.get("seriesAdWrapper");
                                            List<Map<String, Object>> matchesList = (List<Map<String, Object>>) seriesAdWrapper.get("matches");
                                            
                                            for (Map<String, Object> match : matchesList) {
                                                MatchDto dto = convertToMatchDto(match);
                                                if (dto != null) {
                                                    matches.add(dto);
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    } catch (Exception e) {
                        log.error("Error parsing API response: {}", e.getMessage());
                    }
                    
                    return matches;
                })
                .doOnError(error -> log.error("Error fetching live matches: {}", error.getMessage()));
    }
    
    private MatchDto convertToMatchDto(Map<String, Object> matchData) {
        try {
            MatchDto dto = new MatchDto();
            Map<String, Object> matchInfo = (Map<String, Object>) matchData.get("matchInfo");
            
            if (matchInfo != null) {
                dto.setMatchId(matchInfo.get("matchId").toString());
                dto.setSeriesName((String) matchInfo.get("seriesName"));
                dto.setMatchDescription((String) matchInfo.get("matchDesc"));
                dto.setMatchFormat((String) matchInfo.get("matchFormat"));
                dto.setStatus((String) matchInfo.get("status"));
                dto.setVenue((String) matchInfo.getOrDefault("venueInfo", Map.of()).toString());
                
                // Set team information
                if (matchInfo.containsKey("team1") && matchInfo.containsKey("team2")) {
                    Map<String, Object> team1Data = (Map<String, Object>) matchInfo.get("team1");
                    Map<String, Object> team2Data = (Map<String, Object>) matchInfo.get("team2");
                    
                    MatchDto.TeamDto team1 = new MatchDto.TeamDto();
                    team1.setTeamId(team1Data.get("teamId").toString());
                    team1.setName((String) team1Data.get("teamName"));
                    team1.setShortName((String) team1Data.get("teamSName"));
                    dto.setTeam1(team1);
                    
                    MatchDto.TeamDto team2 = new MatchDto.TeamDto();
                    team2.setTeamId(team2Data.get("teamId").toString());
                    team2.setName((String) team2Data.get("teamName"));
                    team2.setShortName((String) team2Data.get("teamSName"));
                    dto.setTeam2(team2);
                }
            }
            
            // Extract score information if available
            if (matchData.containsKey("matchScore")) {
                Map<String, Object> matchScore = (Map<String, Object>) matchData.get("matchScore");
                // Process score data here...
            }
            
            // Set match as live if status indicates
            String status = (String) matchInfo.get("status");
            dto.setLive(status != null && (status.contains("Live") || status.contains("In Progress")));
            
            return dto;
        } catch (Exception e) {
            log.error("Error converting match data to DTO: {}", e.getMessage());
            return null;
        }
    }
    
    public Mono<MatchDto> getMatchDetails(String matchId) {
        return webClient.get()
                .uri("/matches/v1/" + matchId)
                .retrieve()
                .bodyToMono(Map.class)
                .map(response -> {
                    log.info("Match details response: {}", response);
                    MatchDto matchDto = new MatchDto();
                    // Process match details here
                    return matchDto;
                })
                .doOnError(error -> log.error("Error fetching match details: {}", error.getMessage()));
    }
}