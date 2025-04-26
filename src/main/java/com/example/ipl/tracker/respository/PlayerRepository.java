package com.example.ipl.tracker.respository;

import com.example.ipl.tracker.model.Player;
import com.example.ipl.tracker.model.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PlayerRepository extends JpaRepository<Player, String> {
    List<Player> findByTeam(Team team);
    Optional<Player> findByNameContainingIgnoreCase(String name);
}
