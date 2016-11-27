package old.org.grizz.statistics.collector;

import old.org.grizz.model.Entry;
import old.org.grizz.model.Tag;
import old.org.grizz.service.TagExtractor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Grizz on 2014-07-05.
 */
@Service
public class UsersNotTaggingCounter implements StatCollector {
    private Set<String> usersNotTagging = new HashSet<>();

    @Autowired
    private TagExtractor tagExtractor;

    @Override
    public void collect(Entry entry) {
        Set<Tag> tags = tagExtractor.extract(entry.getBody());

        if(tags.isEmpty()) {
            usersNotTagging.add(entry.getAuthor());
        }
    }

    @Override
    public void reset() {
        usersNotTagging.clear();
    }

    public int getNumberOfNotTaggingUsers() {
        return usersNotTagging.size();
    }
}
