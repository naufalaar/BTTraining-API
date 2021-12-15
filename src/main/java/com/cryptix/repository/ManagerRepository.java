package com.cryptix.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cryptix.entity.Manager;

public interface ManagerRepository extends JpaRepository<Manager, Integer>{
	public Manager findByUsername(String username);
	public Manager findByManagerId(int managerId);
}