package org.grizz.statistics.collector;

import org.grizz.model.Entry;
import org.grizz.model.EntryComment;
import org.grizz.model.User;
import org.springframework.stereotype.Service;

@Service
public class MostActiveUser extends AbstractStatCounter {

	@Override
	public void collect(Entry entry) {
		//count entry author
		addToCounter(entry.getAuthor(), 1);
		
		for (User voter : entry.getVoters()) {
			//count voters on entry
			addToCounter(voter.getAuthor(), 1);
		}
		
		for (EntryComment comment : entry.getComments()) {
			//count comment author
			addToCounter(comment.getAuthor(), 1);
			
			for (User voter : comment.getVoters()) {
				//count comment voters
				addToCounter(voter.getAuthor(), 1);
			}
		}
	}
	
	public String getMostActiveUser() {
		return getEntryWithHighestCount().getKey().toString();
	}
	
	public int getNumberOfActions() {
		return getEntryWithHighestCount().getValue();
	}
}
