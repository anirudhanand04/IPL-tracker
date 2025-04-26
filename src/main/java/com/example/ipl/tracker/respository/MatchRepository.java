package com.example.ipl.tracker.respository;

import com.example.ipl.tracker.model.Match;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MatchRepository extends JpaRepository<Match, String> {
    List<Match> findByIsLiveTrue();
    List<Match> findBySeriesNameContainingIgnoreCase(String seriesName);
    List<Match> findByTeam1TeamIdOrTeam2TeamId(String team1Id, String team2Id);
}
