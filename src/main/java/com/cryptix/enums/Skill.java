package com.cryptix.enums;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonValue;

public enum Skill {
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
	quality(11, "quality"),
	remarkable(12,"remarkable"),
	wonderful(13,"wonderful"),
	exceptional(14,"exceptional"),
	sensational(15,"sensational"),
	exquisite(16,"exquisite"),
	masterful(17,"masterful"),
	miraculous(18,"miraculous"),
	phenomenal(19,"phenomenal"),
	elite(20,"elite"),
	elite1(21,"elite +1"),
	elite2(22,"elite +2"),
	elite3(23,"elite +3"),
	elite4(24,"elite +4"),
	elite5(25,"elite +5"),
	elite6(26,"elite +6"),
	elite7(27,"elite +7"),
	elite8(28,"elite +8"),
	elite9(29,"elite +9"),
	elite10(30,"elite +10");
	
	private int value;
	private String level;
	
	private static final Map<Integer, Skill> lookup = new HashMap<Integer, Skill>();

    static {
        for (Skill skill : Skill.values()) {
            lookup.put(skill.getValue(), skill);
        }
    }

	public int getValue() {
		return value;
	}
	
	@JsonValue
	public String getLevel() {
		return level;
	}
	
	private Skill(int value, String level) {
		this.level = level;
		this.value = value;
	}
	
	public static Skill get(int value) {
        return lookup.get(value);
    }
}
