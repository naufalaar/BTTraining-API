package com.cryptix.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cryptix.entity.Manager;
import com.cryptix.repository.ManagerRepository;

@Service
public class ManagerService {
	
	@Autowired
	ManagerRepository managerRepository;
	
	public Manager getManager(int managerId) {
		return managerRepository.findByManagerId(managerId);
	}
	
	public void saveManager(Manager manager) {
		if(manager.getFranchise().getTeamId() == 0) {
			manager.setFranchise(null);
		}
		managerRepository.save(manager);
	}
	
	public Manager getManagerByUsername(Manager manager) {
		return managerRepository.findByUsername(manager.getUsername());
	}

}
