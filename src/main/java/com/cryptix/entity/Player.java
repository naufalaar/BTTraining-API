package com.cryptix.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.cryptix.enums.Aggression;
import com.cryptix.enums.BowlerType;
import com.cryptix.enums.Skill;
import com.cryptix.enums.Hand;
import com.cryptix.enums.PlayerType;
import com.cryptix.enums.Preference;
import com.cryptix.enums.Leadership;
import com.cryptix.enums.Stamina;
import com.cryptix.enums.Status;
import com.cryptix.enums.Trait;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonRawValue;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Player {
	
	@Id
	private int playerId;
	private String firstName;
	private String lastName;
	private short age;
	@Enumerated(EnumType.STRING)
	private Aggression battingAggression;
	@Enumerated(EnumType.STRING)
	private Hand battingHand;
	@Enumerated(EnumType.STRING)
	private Aggression bowlingAggression;
	@Enumerated(EnumType.STRING)
	private BowlerType bowlerType;
	@Enumerated(EnumType.STRING)
	private Leadership leadership;
	@Enumerated(EnumType.STRING)
	private Skill experience;
	@ManyToOne
    @JoinColumn(name="team")
	@JsonRawValue
	private Team team;
	private int wage;
	@Enumerated(EnumType.STRING)
	private Preference preference;
	@Enumerated(EnumType.STRING)
	private Trait trait;
	@Enumerated(EnumType.STRING)
	private Stamina stamina;
	@Enumerated(EnumType.STRING)
	private Skill wicketKeeping;
	@Enumerated(EnumType.STRING)
	private Skill batting;
	@Enumerated(EnumType.STRING)
	private Skill concentration;
	@Enumerated(EnumType.STRING)
	private Skill bowling;
	@Enumerated(EnumType.STRING)
	private Skill consistency;
	@Enumerated(EnumType.STRING)
	private Skill fielding;
	@Enumerated(EnumType.STRING)
	private PlayerType playerType;
	@Enumerated(EnumType.STRING)
	private Status playerStatus;
	@OneToMany(mappedBy="playerSkillChanges")
	private List<SkillChange> playerSkillChanges;
	@OneToMany(mappedBy="player")
	private List<TrainingSession> trainingSessions;
	
	public int getPlayerId() {
		return playerId;
	}
	public void setPlayerId(int playerId) {
		this.playerId = playerId;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public short getAge() {
		return age;
	}
	public void setAge(short age) {
		this.age = age;
	}
	public Aggression getBattingAggression() {
		return battingAggression;
	}
	public void setBattingAggression(Aggression battingAggression) {
		this.battingAggression = battingAggression;
	}
	public Hand getBattingHand() {
		return battingHand;
	}
	public void setBattingHand(Hand battingHand) {
		this.battingHand = battingHand;
	}
	public Aggression getBowlingAggression() {
		return bowlingAggression;
	}
	public void setBowlingAggression(Aggression bowlingAggression) {
		this.bowlingAggression = bowlingAggression;
	}
	public BowlerType getBowlerType() {
		return bowlerType;
	}
	public void setBowlerType(BowlerType bowlerType) {
		this.bowlerType = bowlerType;
	}
	public Leadership getLeadership() {
		return leadership;
	}
	public void setLeadership(Leadership leadership) {
		this.leadership = leadership;
	}
	public Skill getExperience() {
		return experience;
	}
	public void setExperience(Skill experience) {
		this.experience = experience;
	}
	public Team getTeam() {
		return team;
	}
	public void setTeam(Team team) {
		this.team = team;
	}
	public int getWage() {
		return wage;
	}
	public void setWage(int wage) {
		this.wage = wage;
	}
	public Preference getPreference() {
		return preference;
	}
	public void setPreference(Preference preference) {
		this.preference = preference;
	}
	public Trait getTrait() {
		return trait;
	}
	public void setTrait(Trait trait) {
		this.trait = trait;
	}
	public Stamina getStamina() {
		return stamina;
	}
	public void setStamina(Stamina stamina) {
		this.stamina = stamina;
	}
	public Skill getWicketKeeping() {
		return wicketKeeping;
	}
	public void setWicketKeeping(Skill wicketKeeping) {
		this.wicketKeeping = wicketKeeping;
	}
	public Skill getBatting() {
		return batting;
	}
	public void setBatting(Skill batting) {
		this.batting = batting;
	}
	public Skill getConcentration() {
		return concentration;
	}
	public void setConcentration(Skill concentration) {
		this.concentration = concentration;
	}
	public Skill getBowling() {
		return bowling;
	}
	public void setBowling(Skill bowling) {
		this.bowling = bowling;
	} 
	public Skill getConsistency() {
		return consistency;
	}
	public void setConsistency(Skill consistency) {
		this.consistency = consistency;
	}
	public Skill getFielding() {
		return fielding;
	}
	public void setFielding(Skill fielding) {
		this.fielding = fielding;
	}
	public PlayerType getPlayerType() {
		return playerType;
	}
	public void setPlayerType(PlayerType playerType) {
		this.playerType = playerType;
	}
	public Status getPlayerStatus() {
		return playerStatus;
	}
	public void setPlayerStatus(Status playerStatus) {
		this.playerStatus = playerStatus;
	}
	public List<SkillChange> getPlayerSkillChanges() {
		return playerSkillChanges;
	}
	public void setPlayerSkillChanges(List<SkillChange> playerSkillChanges) {
		this.playerSkillChanges = playerSkillChanges;
	}
	public List<TrainingSession> getTrainingSessions() {
		return trainingSessions;
	}
	public void setTrainingSessions(List<TrainingSession> trainingSessions) {
		this.trainingSessions = trainingSessions;
	}
	public String toString() {
		return Integer.toString(playerId);
	}
	
}
