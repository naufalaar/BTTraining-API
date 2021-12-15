package com.cryptix.enums;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonValue;

public enum Trait {
	
	None ("no trait"),
	FCSpecialist ("FC Specialist"),
	ODSpecialist ("OD Specialist"),
	BT20Specialist ("BT20 Specialist"),
	StrikeController ("Strike Controller"),
	GoodEye ("Good Eye"),
	GapFinder ("Gap Finder"),
	Cheetah ("Cheetah"),
	Finisher ("Finisher"),
	KillerInstinct ("Killer Instinct"),
	PartnershipBreaker ("Partnership Breaker"),
	Committed ("Committed"),
	Lazy ("Lazy"),
	QuickLearner ("Quick Learner"),
	Mastermind ("Mastermind"),
	Famous ("Famous"),
	Loyal ("Loyal"),
	PartyAnimal  ("Party Animal");
	
	private String trait;
	private static final Map<String, Trait> lookup = new HashMap<String, Trait>();

    static {
        for (Trait trait : Trait.values()) {
            lookup.put(trait.getLevel(), trait);
        }
    }

    @JsonValue
	public String getLevel() {
		return trait;
	}
	
	private Trait(String trait) {
		this.trait = trait;
	}
	
	public static Trait get(String trait) {
        return lookup.get(trait);
    }
}
