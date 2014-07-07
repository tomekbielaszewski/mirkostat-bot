package org.grizz.statistics.collector;

import org.grizz.model.EmbedType;
import org.grizz.model.Entry;
import org.grizz.model.EntryComment;
import org.springframework.stereotype.Service;

/**
 * Created by Grizz on 2014-07-05.
 */
@Service
public class EmbedCounter extends AbstractStatCounter {
    @Override
    public void collect(Entry entry) {
        if(entry.getEmbed() != null) {
            addToCounter(entry.getEmbed().getType(), 1);
        }

        for (EntryComment entryComment : entry.getComments()) {
            if(entryComment.getEmbed() != null) {
                addToCounter(entryComment.getEmbed().getType(), 1);
            }
        }
    }

    public int getNumberOfEmbedTypes() {
        return getCounter().size();
    }

    public EmbedType getEmbedTypeOnPosition(int position) {
        return (EmbedType) getEntryOnPosition(position).getKey();
    }

    public int getEmbedTypeCountOnPosition(int position) {
        return getEntryOnPosition(position).getValue();
    }
}
