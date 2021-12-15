package com.cryptix.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class Team {
	
	@Id
	private int teamId;
	private String teamName;
	@OneToOne(mappedBy="mainTeam")
	@JsonIgnore
	private Manager mainTeam;
	@OneToOne(mappedBy="franchise")
	@JsonIgnore
	private Manager franchise;
	@OneToMany(mappedBy="team")
	@JsonIgnore
	private List<Player> teamPlayers;
	@OneToMany(mappedBy="team")
	@JsonIgnore
	private List<TrainingSession> teamSessions;

	public int getTeamId() {
		return teamId;
	}

	public void setTeamId(int teamId) {
		this.teamId = teamId;
	}

	public String getTeamName() {
		return teamName;
	}

	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}

	public List<Player> getTeamPlayers() {
		return teamPlayers;
	}

	public Manager getMainTeam() {
		return mainTeam;
	}

	public void setMainTeam(Manager mainTeam) {
		this.mainTeam = mainTeam;
	}

	public Manager getFranchise() {
		return franchise;
	}

	public void setFranchise(Manager franchise) {
		this.franchise = franchise;
	}

	public void setTeamPlayers(List<Player> teamPlayers) {
		this.teamPlayers = teamPlayers;
	}
	public String toString() {
		return Integer.toString(teamId);
	}

}
