package old.org.grizz.statistics.collector;

import old.org.grizz.model.Entry;
import old.org.grizz.model.Tag;
import old.org.grizz.service.TagExtractor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * Created by Grizz on 2015-01-12.
 */
@Service
public class TagVoteCounter extends AbstractStatCounter {
    @Autowired
    private TagExtractor tagExtractor;

    @Override
    public void collect(Entry entry) {
        Set<Tag> tags = tagExtractor.extract(entry.getBody());
        for (Tag tag : tags) {
            addToCounter(tag.getName(), entry.getVotes());
        }
    }

    public int getTagVoteRate(int position) {
        return getEntryOnPosition(position).getValue();
    }

    public String getTagVote(int position) {
        return (String) getEntryOnPosition(position).getKey();
    }
}
