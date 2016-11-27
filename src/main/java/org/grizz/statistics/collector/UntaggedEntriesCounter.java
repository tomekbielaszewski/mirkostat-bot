package org.grizz.statistics.collector;

import org.grizz.model.Entry;
import org.grizz.model.Tag;
import org.grizz.service.TagExtractor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * Created by Grizz on 2014-07-05.
 */
@Service
public class UntaggedEntriesCounter implements StatCollector {
    private int totalUstaggedEntries;

    @Autowired
    private TagExtractor tagExtractor;

    @Override
    public void collect(Entry entry) {
        Set<Tag> tags = tagExtractor.extract(entry.getBody());

        if(tags.isEmpty()) {
            totalUstaggedEntries++;
        }
    }

    @Override
    public void reset() {
        totalUstaggedEntries = 0;
    }

    public int getTotalUstaggedEntries() {
        return totalUstaggedEntries;
    }
}
