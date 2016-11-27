package old.org.grizz.statistics.collector;

import old.org.grizz.model.Entry;
import old.org.grizz.model.EntryComment;
import org.springframework.stereotype.Service;

/**
 * Created by Grizz on 2014-07-05.
 */
@Service
public class VoteCounter implements StatCollector {
    private int totalNumberOfVotes;

    @Override
    public void collect(Entry entry) {
        totalNumberOfVotes += entry.getVotes();

        for (EntryComment entryComment : entry.getComments()) {
            totalNumberOfVotes += entryComment.getVotes();
        }
    }

    @Override
    public void reset() {
        totalNumberOfVotes = 0;
    }

    public int getTotalNumberOfVotes() {
        return totalNumberOfVotes;
    }
}
