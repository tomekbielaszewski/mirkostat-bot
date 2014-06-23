package org.grizz.statistics.collector;

import org.grizz.model.Entry;
import org.springframework.stereotype.Service;

@Service
public class EntryLengthCollector implements StatCollector {
	private int overallLength;

	@Override
	public void collect(Entry entry) {
		overallLength += entry.getBody().length();
	}

	@Override
	public void reset() {
		overallLength = 0;
	}
	
	public int getOverallLength() {
		return overallLength;
	}
}
