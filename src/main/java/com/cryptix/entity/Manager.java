package com.cryptix.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Manager {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int managerId;
	private String username;
	private String password;
	@OneToOne(cascade = {CascadeType.ALL})
	@JoinColumn(name="mainTeam")
	private Team mainTeam;
	@OneToOne(cascade = {CascadeType.ALL})
	@JoinColumn(name="franchise")
	private Team franchise;
	
	public int getManagerId() {
		return managerId;
	}
	public void setManagerId(int userId) {
		this.managerId = userId;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Team getMainTeam() {
		return mainTeam;
	}
	public void setMainTeam(Team mainTeam) {
		this.mainTeam = mainTeam;
	}
	public Team getFranchise() {
		return franchise;
	}
	public void setFranchise(Team franchise) {
		this.franchise = franchise;
	}
	
	

}
