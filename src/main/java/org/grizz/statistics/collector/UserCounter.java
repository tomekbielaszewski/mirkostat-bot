package org.grizz.statistics.collector;

import java.util.HashSet;
import java.util.Set;

import org.grizz.model.Entry;
import org.springframework.stereotype.Service;

@Service
public class UserCounter implements StatCollector {
	private Set<String> users = new HashSet<>();

	@Override
	public void collect(Entry entry) {
		users.add(entry.getAuthor());
	}

	@Override
	public void reset() {
		users.clear();
	}
	
	public int getNumberOfUsers() {
		return users.size();
	}

}
