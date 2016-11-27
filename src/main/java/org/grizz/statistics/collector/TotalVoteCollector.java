package org.grizz.statistics.collector;

import org.grizz.model.Entry;
import org.grizz.model.EntryComment;
import org.springframework.stereotype.Service;

@Service
public class TotalVoteCollector extends AbstractStatCounter {

	@Override
	public void collect(Entry entry) {
		addToCounter(entry.getAuthor(), entry.getVotes());
		
		for (EntryComment comment : entry.getComments()) {
			addToCounter(comment.getAuthor(), comment.getVotes());
		}
	}
	
	public String getBestVotedUser() {
		return getEntryWithHighestCount().getKey().toString();
	}
	
	public int getBestVotes() {
		return getEntryWithHighestCount().getValue();
	}

}
