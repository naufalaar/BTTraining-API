package com.cryptix.service;

import java.io.File;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cryptix.entity.Player;
import com.cryptix.entity.Summary;
import com.cryptix.entity.Team;
import com.cryptix.enums.Aggression;
import com.cryptix.enums.BowlerType;
import com.cryptix.enums.Skill;
import com.cryptix.enums.Hand;
import com.cryptix.enums.Preference;
import com.cryptix.enums.Leadership;
import com.cryptix.enums.PlayerType;
import com.cryptix.enums.Stamina;
import com.cryptix.enums.Trait;
import com.cryptix.repository.PlayerRepository;

@Service
public class PlayerService {
	
	@Autowired
	PlayerRepository playerRepository;
	
	public Player findPlayer(int playerId) {
		return playerRepository.getById(playerId);
	}
	
	public List<Player> getAllPlayers(){
		return playerRepository.findAll();
	}
	
	public void savePlayer(Player player) {
		playerRepository.save(player);
	}
	
	public List<Player> findAllByTeam(Team team){
		List<Player> players = playerRepository.findAllByTeam(team).stream().sorted(Comparator.comparing(Player::getLastName)).collect(Collectors.toList());
		for(Player player: players) {
			if (player.getBowling().getValue() >= 5) {
				if (player.getBatting().getValue() >= 5) 
					player.setPlayerType(PlayerType.AllRounder);
				else
					player.setPlayerType(PlayerType.Bowler);
			}
			else if (player.getBatting().getValue() >= 5) {
				if (player.getWicketKeeping().getValue() >= 5) 
					player.setPlayerType(PlayerType.WicketKeeper);
				else
					player.setPlayerType(PlayerType.Batter);
			}
			else
				player.setPlayerType(PlayerType.Useless);
		}
		return players;
	}
	
	public Summary getTeamSummary(Team team) {
		List<Player> players = findAllByTeam(team);
		Summary summary = new Summary();
		if (players.size() > 0) {
			summary.setWages(players.stream().mapToInt(Player::getWage).sum());
			summary.setPlayers(players.stream().count());
			summary.setAvgFielding(Skill.get((int) Math.round(players.stream().mapToInt(player -> player.getFielding().getValue()).average().getAsDouble())).getLevel());
			summary.setAvgExperience(Skill.get((int) Math.round(players.stream().mapToInt(player -> player.getExperience().getValue()).average().getAsDouble())).getLevel());
			summary.setAvgStamina(Skill.get((int) Math.round(players.stream().mapToInt(player -> player.getStamina().getValue()).average().getAsDouble())).getLevel());
			summary.setBowlers(players.stream().filter(player -> player.getPlayerType().equals(PlayerType.Bowler) || player.getPlayerType().equals(PlayerType.AllRounder)).count());
			summary.setWicketKeepers(players.stream().filter(player -> player.getPlayerType().equals(PlayerType.WicketKeeper)).count());
			summary.setLhBatters(players.stream().filter(player -> player.getPlayerType().equals(PlayerType.Batter) || player.getPlayerType().equals(PlayerType.WicketKeeper)|| player.getPlayerType().equals(PlayerType.AllRounder)).filter(player -> player.getBattingHand().equals(Hand.LH)).count());
			summary.setRhBatters(players.stream().filter(player -> player.getPlayerType().equals(PlayerType.Batter) || player.getPlayerType().equals(PlayerType.WicketKeeper)|| player.getPlayerType().equals(PlayerType.AllRounder)).filter(player -> player.getBattingHand().equals(Hand.RH)).count());
			summary.setfBowlers(players.stream().filter(player -> player.getPlayerType().equals(PlayerType.Bowler) || player.getPlayerType().equals(PlayerType.AllRounder)).filter(player -> player.getBowlerType().equals(BowlerType.LF) || player.getBowlerType().equals(BowlerType.RF)).count());
			summary.setFmBowlers(players.stream().filter(player -> player.getPlayerType().equals(PlayerType.Bowler) || player.getPlayerType().equals(PlayerType.AllRounder)).filter(player -> player.getBowlerType().equals(BowlerType.LFM) || player.getBowlerType().equals(BowlerType.RFM)).count());
			summary.setmBowlers(players.stream().filter(player -> player.getPlayerType().equals(PlayerType.Bowler) || player.getPlayerType().equals(PlayerType.AllRounder)).filter(player -> player.getBowlerType().equals(BowlerType.LM) || player.getBowlerType().equals(BowlerType.RM)).count());
			summary.setSpinBowlers(players.stream().filter(player -> player.getPlayerType().equals(PlayerType.Bowler) || player.getPlayerType().equals(PlayerType.AllRounder)).filter(player -> player.getBowlerType().equals(BowlerType.RHFS) || player.getBowlerType().equals(BowlerType.RHWS) || player.getBowlerType().equals(BowlerType.LHFS) || player.getBowlerType().equals(BowlerType.LHWS)).count());
			summary.setAvgBatting(Skill.get((int) Math.round(players.stream().filter(player -> player.getPlayerType().equals(PlayerType.Batter) || player.getPlayerType().equals(PlayerType.WicketKeeper) || player.getPlayerType().equals(PlayerType.AllRounder)).mapToInt(player -> player.getBatting().getValue()).average().getAsDouble())).getLevel());
			summary.setAvgBowling(Skill.get((int) Math.round(players.stream().filter(player -> player.getPlayerType().equals(PlayerType.Bowler) || player.getPlayerType().equals(PlayerType.AllRounder)).mapToInt(player -> player.getBowling().getValue()).average().getAsDouble())).getLevel());
		}
		return summary;
	}
	
	public Player parseImportedPlayer(String importedPlayer) {
		
		Pattern basicDetails = Pattern.compile("\"(\\d*\\.\\s|)(.*) - (.*) yo, BT Rating=(.*), Wage=£(\\S+) (A|An) (.*) (.*) batter and (a|an) (\\S+) (.*) bowler. He has (.*) leadership skills and (.*) experience. He (has|favours) (.*) (and is a|and|but is a|but has) (.*). He currently has (.*) batting form, (.*) bowling form and (.*) fitness. Stamina: (.*) Wicket Keeping: (.*) Batting: (.*) Concentration: (.*) Bowling: (.*) Consistency: (.*) Fielding: (.*)\"");
		Player player = new Player();
		Matcher basicDetailsMatcher = basicDetails.matcher(importedPlayer.replaceAll("shortcut", "").replaceAll("transfer listed", "").replaceAll("   ", " ").replaceAll("  ", " "));
		if (basicDetailsMatcher.matches()) {
				player.setFirstName(basicDetailsMatcher.group(2).split(" ", 2)[0]);
				player.setLastName(basicDetailsMatcher.group(2).split(" ", 2)[1]);
				player.setAge(Short.parseShort(basicDetailsMatcher.group(3)));
				//player.setWage(Integer.parseInt(basicDetailsMatcher.group(5).replaceAll(",", "").replaceAll("shortcut", "")));
				player.setWage(Integer.parseInt(basicDetailsMatcher.group(5).replaceAll(",", "")));
				
				player.setBattingAggression(Aggression.valueOf(basicDetailsMatcher.group(7)));
				player.setBattingHand(Hand.valueOf(basicDetailsMatcher.group(8)));
				player.setBowlingAggression(Aggression.valueOf(basicDetailsMatcher.group(10)));
				player.setBowlerType(BowlerType.get(basicDetailsMatcher.group(11)));
				player.setLeadership(Leadership.valueOf(basicDetailsMatcher.group(12)));
				player.setExperience(Skill.valueOf(basicDetailsMatcher.group(13)));
				player.setPreference(Preference.get(basicDetailsMatcher.group(15)));
				player.setTrait(Trait.get(basicDetailsMatcher.group(17)));
				
				
				player.setStamina(Stamina.get(basicDetailsMatcher.group(21)));
				player.setWicketKeeping(Skill.valueOf(basicDetailsMatcher.group(22)));
				/* TODO: Fix the values for elite + */
				player.setBatting(Skill.valueOf(basicDetailsMatcher.group(23)));
				player.setConcentration(Skill.valueOf(basicDetailsMatcher.group(24)));
				
				player.setBowling(Skill.valueOf(basicDetailsMatcher.group(25)));
				player.setConsistency(Skill.valueOf(basicDetailsMatcher.group(26)));
				
				player.setFielding(Skill.valueOf(basicDetailsMatcher.group(27)));
			}
		return player;
	}

}
