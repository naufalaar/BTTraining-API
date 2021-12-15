package com.cryptix.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cryptix.entity.Team;
import com.cryptix.entity.TrainingSession;

public interface TrainingSessionRepository extends JpaRepository<TrainingSession, Integer>{
	
	public List<TrainingSession> findAllByTeam(Team team);
}