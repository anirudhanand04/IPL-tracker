package com.example.ipl.tracker.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ScoreDto {
    private Integer runs;
    private Integer wickets;
    private Double overs;
    private Integer inning;
}
