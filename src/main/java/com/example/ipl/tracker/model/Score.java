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
public class Score {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "match_id")
    private Match match;
    
    @ManyToOne
    @JoinColumn(name = "team_id")
    private Team team;
    
    private Integer runs;
    private Integer wickets;
    private Double overs;
    private LocalDateTime updatedAt;
    private Integer inning;
}
