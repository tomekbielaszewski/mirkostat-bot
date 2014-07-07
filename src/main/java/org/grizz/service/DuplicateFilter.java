package org.grizz.service;

import java.util.HashSet;
import java.util.Set;

import org.grizz.model.Entry;
import org.springframework.stereotype.Service;

@Service
public class DuplicateFilter {
	private Set<Entry> entries = new HashSet<>();

	public boolean isDuplicated(Entry entry) {
		return !entries.add(entry);
	}
	
	public void reset() {
		entries.clear();
	}
	
}
