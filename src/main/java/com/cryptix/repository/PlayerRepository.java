package com.cryptix.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.cryptix.entity.Player;
import com.cryptix.entity.Team;

public interface PlayerRepository extends JpaRepository<Player, Integer>{
	public List<Player> findAllByTeam(Team team);
}