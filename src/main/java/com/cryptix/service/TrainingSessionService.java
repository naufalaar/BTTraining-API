package com.cryptix.service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cryptix.entity.Player;
import com.cryptix.entity.Team;
import com.cryptix.entity.TrainingSession;
import com.cryptix.repository.TrainingSessionRepository;

@Service
public class TrainingSessionService {
	
	@Autowired
	TrainingSessionRepository trainingSessionRepository;
	@Autowired
	PlayerService playerService;
	
	public void saveTrainingSession(TrainingSession trainingSession) {
		trainingSessionRepository.save(trainingSession);
	}
	
	public Optional<TrainingSession> getTrainingSession(TrainingSession trainingSession) {
		return trainingSessionRepository.findById(trainingSession.getId());
	}
	
	public List<TrainingSession> getAllTrainingSession(Team team) {
		return trainingSessionRepository.findAllByTeam(team);
	}
	
//	public List<TrainingSession> saveMultipleSession(List<TrainingSession> trainingSessions) {
	public List<Player> saveMultipleSession(List<TrainingSession> trainingSessions) {
		Team team = null;
		List<TrainingSession> deleteSessions = trainingSessions.stream().filter(o -> o.getId() != 0 || o.getQuantity() == 0).collect(Collectors.toList());
		if(deleteSessions.size() > 0) {
			team = deleteSessions.get(0).getTeam();
			trainingSessionRepository.deleteAll(deleteSessions);
		}
		trainingSessions.removeIf(item -> item.getQuantity() == 0);
		if (trainingSessions.size() > 0) {
			List<TrainingSession> result = trainingSessionRepository.saveAll(trainingSessions);
			if(team == null) {	
				team = result.get(0).getTeam();
			}
		}
		return playerService.findAllByTeam(team);
//		return getAllTrainingSession(team);
	}
	
	public List<Integer> getCurrentWeek(){
		List<Integer> result = new ArrayList<Integer>();
		
		int startingSeason = 53;
		int startingWeek = 1;
		int noOfWeeks = 16;
		LocalDate startDate = LocalDate.of(2021, 9, 4);
		LocalDate today = LocalDate.now();
		List<LocalDate> listOfSaturdays = startDate.datesUntil(today.plusDays(1)).filter(o->o.getDayOfWeek().equals(DayOfWeek.SATURDAY)).sorted(Comparator.comparing(LocalDate::toEpochDay).reversed()).collect(Collectors.toList());
		int currentSeason = startingSeason;
		int currentWeek = startingWeek;
		int listSize = listOfSaturdays.size();
		if (listSize % noOfWeeks == 0) {
			currentSeason = startingSeason + listSize / noOfWeeks -1 ;
			currentWeek = noOfWeeks;
		}else {
			currentSeason = startingSeason + listSize / noOfWeeks;
			currentWeek = listSize - ((listSize / noOfWeeks) * noOfWeeks);
		}
		result.add(currentSeason);
		result.add(currentWeek);
		return result; 
	}

}
