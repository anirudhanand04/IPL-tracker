package com.example.ipl.tracker.model;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Player {
    
    @Id
    private String playerId;
    
    private String name;
    private String displayName;
    private String playerType; // BATSMAN, BOWLER, ALL_ROUNDER, WICKET_KEEPER
    private String imageUrl;
    
    @ManyToOne
    @JoinColumn(name = "team_id")
    private Team team;
    
    // Add additional player statistics if needed
    private Integer battingPosition;
    private String battingStyle;
    private String bowlingStyle;
}
