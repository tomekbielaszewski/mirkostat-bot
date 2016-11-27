package old.org.grizz.statistics.collector;

import old.org.grizz.model.Entry;
import old.org.grizz.model.EntryComment;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Grizz on 2014-07-04.
 */
@Service
public class EntryCommentCounter extends AbstractStatCounter {
    private Set<EntryComment> comments = new HashSet<>();

    @Override
    public void collect(Entry entry) {
        comments.addAll(entry.getComments());
    }

    public int getNumberOfComments() {
        return comments.size();
    }
}
