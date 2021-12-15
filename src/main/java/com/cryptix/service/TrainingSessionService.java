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

import com.cryptix.entity.Team;
import com.cryptix.entity.TrainingSession;
import com.cryptix.repository.TrainingSessionRepository;

@Service
public class TrainingSessionService {
	
	@Autowired
	TrainingSessionRepository trainingSessionRepository;
	
	public void saveTrainingSession(TrainingSession trainingSession) {
		trainingSessionRepository.save(trainingSession);
	}
	
	public Optional<TrainingSession> getTrainingSession(TrainingSession trainingSession) {
		return trainingSessionRepository.findById(trainingSession.getId());
	}
	
	public List<TrainingSession> getAllTrainingSession(Team team) {
		return trainingSessionRepository.findAllByTeam(team);
	}
	
	public void saveMultipleSession(List<TrainingSession> trainingSessions) {
		trainingSessionRepository.saveAll(trainingSessions);
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
