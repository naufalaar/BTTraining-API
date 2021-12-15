package com.cryptix.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.cryptix.entity.Manager;
import com.cryptix.repository.ManagerRepository;

@Service
public class UserService implements UserDetailsService{
	
	@Autowired
	ManagerRepository managerRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Manager manager = managerRepository.findByUsername(username);
		if (manager == null) {
			throw new UsernameNotFoundException(username);
		}
		
		return new User(manager.getUsername(), manager.getPassword(), new ArrayList<>());
		
		
		
	}
	
	public Manager findByManagerId(short managerId) {
		return managerRepository.findByManagerId(managerId);
	}
	
	

}
