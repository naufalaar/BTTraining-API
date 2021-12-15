package com.cryptix.enums;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonValue;

public enum BowlerType {
	RF ("RF"),
	RFM ("RFM"),
	RM ("RM"), 
	RHFS ("RH finger spin"),
	RHWS ("RH wrist spin"),
	LF ("LF"),
	LFM ("LFM"),
	LM ("LM"), 
	LHFS ("LH finger spin"),
	LHWS ("LH wrist spin");
	
	private String type;
	private static final Map<String, BowlerType> lookup = new HashMap<String, BowlerType>();

    static {
        for (BowlerType bowlerType : BowlerType.values()) {
            lookup.put(bowlerType.getType(), bowlerType);
        }
    }

    @JsonValue
	public String getType() {
		return type;
	}
	
	private BowlerType(String type) {
		this.type = type;
	}
	
	public static BowlerType get(String bowlerType) {
        return lookup.get(bowlerType);
    }
}
