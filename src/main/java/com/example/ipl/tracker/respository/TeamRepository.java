package com.example.ipl.tracker.respository;

import com.example.ipl.tracker.model.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TeamRepository extends JpaRepository<Team, String> {
    Optional<Team> findByNameIgnoreCase(String name);
    Optional<Team> findByShortNameIgnoreCase(String shortName);
}
