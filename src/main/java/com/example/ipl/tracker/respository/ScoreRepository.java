package com.example.ipl.tracker.respository;

import com.example.ipl.tracker.model.Match;
import com.example.ipl.tracker.model.Score;
import com.example.ipl.tracker.model.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ScoreRepository extends JpaRepository<Score, Long> {
    List<Score> findByMatch(Match match);
    List<Score> findByMatchOrderByInningDesc(Match match);
    Optional<Score> findByMatchAndTeamAndInning(Match match, Team team, Integer inning);
}