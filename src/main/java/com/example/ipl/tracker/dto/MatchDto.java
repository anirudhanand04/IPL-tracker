package com.example.ipl.tracker.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MatchDto {
    private String matchId;
    private String seriesName;
    private String matchDescription;
    private String matchFormat;
    private LocalDateTime startTime;
    private String venue;
    private String status;
    private TeamDto team1;
    private TeamDto team2;
    private ScoreDto team1Score;
    private ScoreDto team2Score;
    private boolean isLive;
    
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class TeamDto {
        private String teamId;
        private String name;
        private String shortName;
        private String logoUrl;
    }
}
