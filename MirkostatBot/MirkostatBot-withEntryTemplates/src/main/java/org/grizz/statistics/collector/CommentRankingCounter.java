package org.grizz.statistics.collector;

import org.grizz.model.Entry;
import org.grizz.model.EntryComment;
import org.springframework.stereotype.Service;

@Service
public class CommentRankingCounter extends AbstractStatCounter {
	
	@Override
	public void collect(Entry entry) {
        for (EntryComment entryComment : entry.getComments()) {
            addToCounter(entryComment, entryComment.getVotes());
        }
    }
	
	public EntryComment getCommentOnPosition(int position) {
		return (EntryComment) getEntryOnPosition(position).getKey();
	}
}
