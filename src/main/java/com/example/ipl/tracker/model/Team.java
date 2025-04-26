package com.example.ipl.tracker.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Team {
    
    @Id
    private String teamId;
    
    private String name;
    private String shortName;
    private String logoUrl;
    
    @OneToMany(mappedBy = "team")
    private List<Player> players;
}
