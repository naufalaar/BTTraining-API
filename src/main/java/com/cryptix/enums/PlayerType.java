package com.cryptix.enums;

import com.fasterxml.jackson.annotation.JsonValue;

public enum PlayerType {
	Batter ("Batter"),
	Bowler ("Bowler"),
	AllRounder ("All Rounder"),
	WicketKeeper ("Wicket Keeper"),
	Useless ("Useless");
	
	private String playerType;
	
	@JsonValue
	public String getPlayerType() {
		return playerType;
	}
	
	private PlayerType(String playerType) {
		this.playerType = playerType;
	}
}
