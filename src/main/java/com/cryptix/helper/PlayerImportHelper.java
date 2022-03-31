package com.cryptix.helper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class PlayerImportHelper {
	
	public List<String> identifyPlayers(String importedSquad){
		List<String> temp = new ArrayList<>(Arrays.asList(importedSquad.replace("\"","").split(";")));
		int size = temp.size();
		for (int i=1; size-(19*i) > 0; i++) {
			temp.add(size-(19*i), "#");
		}
		StringBuilder builder = new StringBuilder();
		System.out.println(size + " " + temp.size());
		for(String s: temp) {
			builder.append(s + " ");
		}
		List<String> players = new ArrayList<>(Arrays.asList(builder.toString().split("#")));
		return players;
	}

}
