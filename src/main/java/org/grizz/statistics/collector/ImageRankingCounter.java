package org.grizz.statistics.collector;

import org.grizz.model.EmbedType;
import org.grizz.model.Entry;
import org.grizz.model.EntryComment;
import org.springframework.stereotype.Service;

@Service
public class ImageRankingCounter extends AbstractStatCounter {
	
	@Override
	public void collect(Entry entry) {
        if(isImagePresent(entry)) {
            addToCounter(entry, entry.getVotes());
        }

        for (EntryComment entryComment : entry.getComments()) {
            if(isImagePresent(entry)) {
                addToCounter(entry, entryComment.getVotes());
            }
        }
    }

    public Entry getImageOnPosition(int position) {
        return (Entry) getEntryOnPosition(position).getKey();
    }

    public int getImageCountOnPosition(int position) {
        return getEntryOnPosition(position).getValue();
    }

    private boolean isImagePresent(Entry entry) {
        return entry.getEmbed() != null && entry.getEmbed().getType() == EmbedType.IMAGE;
    }
}
