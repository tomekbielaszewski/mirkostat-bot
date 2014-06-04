package org.grizz.statistics.collector;

import org.grizz.model.Entry;
import org.springframework.stereotype.Service;

@Service
public class UserTypedCharactersCounter extends AbstractStatCounter {
	
	@Override
	public void collect(Entry entry) {
		addToCounter(entry.getAuthor(), entry.getBody().length());
	}
	
	public int getTypedCharactersRate(int position) {
		return getEntryOnPosition(position).getValue();
	}
	
	public String getUserTypedCharactersRate(int position) {
		return (String) getEntryOnPosition(position).getKey();
	}
}
