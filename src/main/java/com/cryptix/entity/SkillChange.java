package com.cryptix.entity;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.cryptix.enums.BattrickSkills;
import com.cryptix.enums.SkillChangeType;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
public class SkillChange {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Enumerated(EnumType.STRING)
	private BattrickSkills skill;
	private String previousLevel;
	private String newLevel;
	private int season;
	private int week;
	@Enumerated(EnumType.STRING)
	private SkillChangeType type;
	@ManyToOne
	@JoinColumn(name="playerSkillChanges")
	@JsonBackReference(value="playerSkillChanges")
	private Player playerSkillChanges;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getPreviousLevel() {
		return previousLevel;
	}
	public void setPreviousLevel(String previousLevel) {
		this.previousLevel = previousLevel;
	}
	public String getNewLevel() {
		return newLevel;
	}
	public void setNewLevel(String newLevel) {
		this.newLevel = newLevel;
	}
	public SkillChangeType getType() {
		return type;
	}
	public void setType(SkillChangeType type) {
		this.type = type;
	}
	public BattrickSkills getSkill() {
		return skill;
	}
	public void setSkill(BattrickSkills skill) {
		this.skill = skill;
	}
	public Player getPlayerSkillChanges() {
		return playerSkillChanges;
	}
	public void setPlayerSkillChanges(Player playerSkillChanges) {
		this.playerSkillChanges = playerSkillChanges;
	}
	public int getSeason() {
		return season;
	}
	public void setSeason(int season) {
		this.season = season;
	}
	public int getWeek() {
		return week;
	}
	public void setWeek(int week) {
		this.week = week;
	}
	
	
}
