package old.org.grizz.statistics.collector;

import old.org.grizz.model.Entry;
import old.org.grizz.model.EntryComment;
import org.springframework.stereotype.Service;

/**
 * Created by Grizz on 2014-07-05.
 */
@Service
public class UserCommentsCounter extends AbstractStatCounter {
    @Override
    public void collect(Entry entry) {
        for (EntryComment entryComment : entry.getComments()) {
            addToCounter(entryComment.getAuthor(), 1);
        }
    }

    public String getUserOnPosition(int position) {
        return getEntryOnPosition(position).getKey().toString();
    }

    public int getUserCountOnPosition(int position) {
        return getEntryOnPosition(position).getValue();
    }
}
