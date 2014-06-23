package org.grizz.statistics.collector;

import org.grizz.model.Entry;
import org.springframework.stereotype.Service;

@Service
public class UserEntryCounter extends AbstractStatCounter {
	
	@Override
	public void collect(Entry entry) {
		addToCounter(entry.getAuthor(), 1);
	}
	
	public int getEntryRate(int position) {
		return getEntryOnPosition(position).getValue();
	}
	
	public String getUserEntryRate(int position) {
		return (String) getEntryOnPosition(position).getKey();
	}
}
