package org.grizz.statistics.collector;

import org.grizz.model.Entry;
import org.springframework.stereotype.Service;

@Service
public class UserEntryCounter extends AbstractStatCounter {
	
	@Override
	public void collect(Entry entry) {
		addToCounter(entry.getAuthor(), 1);
	}
	
	public int getHighestEntryRate() {
		return getEntryWithHighestCount().getValue();
	}
	
	public String getUserWithHighestEntryRate() {
		return (String) getEntryWithHighestCount().getKey();
	}
}
