package com.example.ipl.tracker.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Match {
    
    @Id
    private String matchId;
    
    private String seriesName;
    private String matchDescription;
    private String matchFormat;
    private LocalDateTime startTime;
    private String venue;
    private String status;
    
    @ManyToOne
    @JoinColumn(name = "team1_id")
    private Team team1;
    
    @ManyToOne
    @JoinColumn(name = "team2_id")
    private Team team2;
    
    private boolean isLive;
}
