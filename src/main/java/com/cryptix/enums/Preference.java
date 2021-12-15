package com.cryptix.enums;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonValue;

public enum Preference {
	
	None ("no preference"),
	RHBowler ("Right-Handed Bowlers"),
	LHBowler ("Left-Handed Bowlers"),
	RHBatter ("Right-Handed Batters"),
	LHBatter ("Left-Handed Batters"),
	FBowling ("Fast Bowling"),
	FMBowling ("Fast-Medium Bowling"),
	MBowling ("Medium Bowling"),
	SpinBowling ("Spin Bowling"),
	GreenPitch ("Green Pitches"),
	FlatPitch ("Flat Pitches"),
	HFPitch ("Hard and Fast Pitches"),
	SlowPitch ("Slow Pitches"),
	CrackedPitch ("Cracked Pitches"),
	DustyPitch ("Dusty Pitches"),
	UnevenPitch ("Uneven Pitches");
	
	private String preference;
	private static final Map<String, Preference> lookup = new HashMap<String, Preference>();

    static {
        for (Preference preference : Preference.values()) {
            lookup.put(preference.getLevel(), preference);
        }
    }

    @JsonValue
	public String getLevel() {
		return preference;
	}
	
	private Preference(String preference) {
		this.preference = preference;
	}
	
	public static Preference get(String preference) {
        return lookup.get(preference);
    }
}
