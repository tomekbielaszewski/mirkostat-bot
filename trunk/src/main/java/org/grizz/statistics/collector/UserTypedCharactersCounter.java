package org.grizz.statistics.collector;

import org.grizz.model.Entry;
import org.springframework.stereotype.Service;

@Service
public class UserTypedCharactersCounter extends AbstractStatCounter {
	
	@Override
	public void collect(Entry entry) {
		addToCounter(entry.getAuthor(), entry.getBody().length());
	}
	
	public int getHighestTypedCharactersRate() {
		return getEntryWithHighestCount().getValue();
	}
	
	public String getUserWithHighestTypedCharactersRate() {
		return (String) getEntryWithHighestCount().getKey();
	}
}
