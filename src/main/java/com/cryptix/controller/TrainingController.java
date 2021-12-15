package com.cryptix.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cryptix.entity.Manager;
import com.cryptix.entity.Player;
import com.cryptix.entity.SkillChange;
import com.cryptix.entity.Summary;
import com.cryptix.entity.Team;
import com.cryptix.entity.TrainingSession;
import com.cryptix.service.ManagerService;
import com.cryptix.service.PlayerService;
import com.cryptix.service.SkillChangeService;
import com.cryptix.service.TrainingSessionService;

@RestController
@RequestMapping("/")
public class TrainingController {
	
	@Autowired
	private PlayerService playerService;
	@Autowired
	private ManagerService managerService;
	@Autowired
	private SkillChangeService skillChangeService;
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	@Autowired
	private TrainingSessionService trainingSessionService;
	
	@GetMapping("/allPlayers")
	public List<Player> getAllPlayers(){
		return playerService.getAllPlayers();
	}
	
	@GetMapping("/getManager")
	public Manager getManager(@RequestParam("managerId") int managerId){
		return managerService.getManager(managerId);
	}
	
	@PostMapping("/getManagerByUsername")
	public Manager getManagerByUsername(@RequestBody Manager manager){
		return managerService.getManagerByUsername(manager);
	}
	
	@PostMapping("/sign-up")
    public void signUp(@RequestBody Manager manager) {
        manager.setPassword(bCryptPasswordEncoder.encode(manager.getPassword()));
        managerService.saveManager(manager);
    }
	
	@PostMapping("savePlayer")
	public void savePlayer(@RequestBody Player player) {
		playerService.savePlayer(player);
	}
	
	@PostMapping("/teamPlayers")
	public List<Player> getAllPlayers(@RequestBody Team team){
		return playerService.findAllByTeam(team);
	}
	
	@PostMapping("/parsePlayer")
	public Player parseImportedPlayer(@RequestBody String importedPlayer) {
		return playerService.parseImportedPlayer(importedPlayer);
	}
	
	@PostMapping("/teamSummary")
	public Summary getTeamSummary(@RequestBody Team team) {
		return playerService.getTeamSummary(team);
	}
	
	@PostMapping("/saveSkillChange")
	public List<Player> saveSkillChange(@RequestBody SkillChange skillChange) {
		return skillChangeService.saveSkillChange(skillChange);
	}
	
	@PostMapping("/playerSkillChange")
	public List<SkillChange> getPlayerSkillChange(@RequestBody Player player) {
		return skillChangeService.findAllByPlayer(player);
	}
	
	@PostMapping("/saveTrainingSession")
	public void saveTrainingSession(@RequestBody TrainingSession trainingSession) {
		trainingSessionService.saveTrainingSession(trainingSession);
	}
	
	@PostMapping("/getTrainingSession")
	public Optional<TrainingSession> getTrainingSession(@RequestBody TrainingSession trainingSession) {
		return trainingSessionService.getTrainingSession(trainingSession);
	}
	
	@PostMapping("/getAllTrainingSession")
	public List<TrainingSession> getAllTrainingSession(@RequestBody Team team) {
		return trainingSessionService.getAllTrainingSession(team);
	}
	
	@PostMapping("/saveMultipleSessions")
	public void saveMultipleSessions(@RequestBody List<TrainingSession> trainingSessions) {
		trainingSessionService.saveMultipleSession(trainingSessions);
	}
	
	@PostMapping("/getCurrentWeek")
	public List<Integer> getCurrentWeek(){
		return trainingSessionService.getCurrentWeek();
	}
}