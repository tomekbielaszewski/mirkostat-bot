package org.grizz.statistics;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.grizz.model.Entry;
import org.springframework.stereotype.Service;

@Service
public class UserStats {
	/**
	 * liczy ile wpisow napisal dany uzytkownik Map<Uzytkownik, LiczbaWpisow>
	 */
	private Map<String, Integer> userEntryCounter = new HashMap<>();
	/**
	 * liczy ile znakow napisal dany uzytkownik we wszystkich wpisach Map<Uzytkownik, LiczbaZnakow>
	 */
	private Map<String, Integer> userCharactersCounter = new HashMap<>();
	/**
	 * Zbior nickow. Mozna uzyc to liczenia ile uzytkownikow pisalo na mirko
	 */
	private Set<String> users = new HashSet<>();
	
	public void update(Entry entry) {
		users.add(entry.getAuthor());
		updateEntryCounter(entry.getAuthor());
		updateCharactersCounter(entry.getAuthor(), entry.getBody().length());
	}
	
	public int getNumberOfUsers() {
		return users.size();
	}
	
	public Map<String, Integer> getUserCharactersCounter() {
		return userCharactersCounter;
	}
	
	public Map<String, Integer> getUserEntryCounter() {
		return userEntryCounter;
	}
	
	private void updateCharactersCounter(String author, int length) {
		updateMap(author, length, userCharactersCounter);
	}

	private void updateEntryCounter(String author) {
		updateMap(author, 1, userEntryCounter);
	}

	private void updateMap(String key, Integer value, Map<String, Integer> map){
		if(map.containsKey(key)) {
			Integer i = map.get(key);
			i += value;
			map.put(key, i);
		} else {
			map.put(key, value);
		}
	}
}
