package com.cryptix.repository;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import com.cryptix.entity.Player;
import com.cryptix.entity.SkillChange;

public interface SkillChangeRepository extends JpaRepository<SkillChange, Integer>{
	
	public List<SkillChange> findAllByPlayerSkillChanges(Player player, Sort sort);
}