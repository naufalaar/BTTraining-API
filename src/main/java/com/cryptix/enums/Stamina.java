package com.cryptix.enums;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonValue;

public enum Stamina {
	worthless(1, "worthless"),
	abysmal(2, "abysmal"),
	woeful(3, "woeful"),
	feeble(4, "feeble"),
	mediocre(5, "mediocre"),
	competent(6, "competent"),
	respectable(7, "respectable"),
	proficient(8, "proficient"),
	strong(9, "strong"),
	superb(10, "superb"),
	superbPlus (11, "superb*");
	
	private String stamina;
	private int value;
	
	private static final Map<String, Stamina> lookup = new HashMap<String, Stamina>();

    static {
        for (Stamina stamina : Stamina.values()) {
            lookup.put(stamina.getStamina(), stamina);
        }
    }

    @JsonValue
	public String getStamina() {
		return stamina;
	}
    
    public int getValue() {
		return value;
	}
    
	
	private Stamina(int value, String stamina) {
		this.stamina = stamina;
		this.value = value;
	}
	
	public static Stamina get(String stamina) {
        return lookup.get(stamina);
    }
}
