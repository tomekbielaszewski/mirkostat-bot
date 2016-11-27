package org.grizz.statistics.collector;

import org.grizz.model.Entry;
import org.grizz.model.EntryComment;
import org.grizz.model.User;
import org.springframework.stereotype.Service;

/**
 * Created by Grizz on 2014-07-05.
 */@Service
public class GenderActivityCollector extends AbstractStatCounter {
    @Override
    public void collect(Entry entry) {
        addToCounter(entry.getAuthorSex(), 1);

        for (User user : entry.getVoters()) {
            addToCounter(user.getAuthorSex(), 1);
        }

        for (EntryComment entryComment : entry.getComments()) {
            addToCounter(entryComment.getAuthorSex(), 1);

            for (User user : entryComment.getVoters()) {
                addToCounter(user.getAuthorSex(), 1);
            }
        }
    }

    public int getNumberOfGenders() {
        return getCounter().size();
    }

    public String getGenderOnPosition(int position) {
        return getEntryOnPosition(position).getKey().toString();
    }

    public int getGenderCountOnPosition(int position) {
        return getEntryOnPosition(position).getValue();
    }
}
