package com.cryptix.service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.cryptix.entity.Player;
import com.cryptix.entity.SkillChange;
import com.cryptix.entity.Team;
import com.cryptix.enums.BattrickSkills;
import com.cryptix.enums.Skill;
import com.cryptix.enums.Stamina;
import com.cryptix.repository.SkillChangeRepository;

@Service
public class SkillChangeService {
	
	@Autowired
	SkillChangeRepository skillChangeRepository;
	@Autowired
	PlayerService playerService;
	
	public List<Player> saveSkillChange(SkillChange skillChange) {
		skillChangeRepository.save(skillChange);
		Player player = playerService.findPlayer(skillChange.getPlayerSkillChanges().getPlayerId());
		switch (skillChange.getSkill()) {
		case batting:
			player.setBatting(Skill.valueOf(skillChange.getNewLevel()));
			break;
		case bowling:
			player.setBowling(Skill.valueOf(skillChange.getNewLevel()));
			break;
		case fielding:
			player.setFielding(Skill.valueOf(skillChange.getNewLevel()));
			break;
		case concentration:
			player.setConcentration(Skill.valueOf(skillChange.getNewLevel()));
			break;
		case consistency:
			player.setConsistency(Skill.valueOf(skillChange.getNewLevel()));
			break;
		case stamina:
			player.setStamina(Stamina.valueOf(skillChange.getNewLevel()));
			break;
		case wicketKeeping:
			player.setWicketKeeping(Skill.valueOf(skillChange.getNewLevel()));
			break;
		default:
			break;
		}
		
		playerService.savePlayer(player);
		return playerService.findAllByTeam(player.getTeam());
		
	}
	
	public List<Player> saveHistoricSkillChange(SkillChange skillChange) {
		skillChangeRepository.save(skillChange);
		Player player = playerService.findPlayer(skillChange.getPlayerSkillChanges().getPlayerId());
		return playerService.findAllByTeam(player.getTeam());
		
	}
	
	public List<Player> removeSkillChange(List<SkillChange> skillChanges) {
		skillChangeRepository.deleteAll(skillChanges);
		Player player = playerService.findPlayer(skillChanges.get(0).getPlayerSkillChanges().getPlayerId());
		return playerService.findAllByTeam(player.getTeam());
		
	}
	
	public List<SkillChange> findAllByPlayer(Player player){
		return skillChangeRepository.findAllByPlayerSkillChanges(player, Sort.by("season").descending().and(Sort.by("week").descending()));
	}

}
