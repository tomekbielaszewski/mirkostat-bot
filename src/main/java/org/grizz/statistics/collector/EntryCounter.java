package org.grizz.statistics.collector;

import java.util.HashSet;
import java.util.Set;

import org.grizz.model.Entry;
import org.springframework.stereotype.Service;

@Service
public class EntryCounter implements StatCollector {
	private Set<Entry> entries = new HashSet<>();

	@Override
	public void collect(Entry entry) {
		entries.add(entry);
	}

	@Override
	public void reset() {
		entries.clear();
	}
	
	public int getNumberOfEntries() {
		return entries.size();
	}

}
