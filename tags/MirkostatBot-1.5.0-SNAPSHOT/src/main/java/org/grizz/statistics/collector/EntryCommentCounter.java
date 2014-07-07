package org.grizz.statistics.collector;

import org.grizz.model.Entry;
import org.springframework.stereotype.Service;

/**
 * Created by Grizz on 2014-07-04.
 */
@Service
public class EntryCommentCounter extends AbstractStatCounter {
    private int numberOfComments;

    @Override
    public void collect(Entry entry) {
        numberOfComments += entry.getComments().size();
    }

    public int getNumberOfComments() {
        return numberOfComments;
    }
}
