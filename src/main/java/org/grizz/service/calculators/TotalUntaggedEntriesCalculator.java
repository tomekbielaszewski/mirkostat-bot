package org.grizz.service.calculators;

import org.grizz.model.Entry;
import org.grizz.model.Tag;
import org.grizz.service.calculators.structures.TagExtractor;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class TotalUntaggedEntriesCalculator implements StatisticsCalculator {
    public static final String NAME = "untaggedEntriesCounter";
    private int untaggedEntriesCounter = 0;

    @Override
    public void consume(Set<Entry> entries) {
        TagExtractor tagExtractor = new TagExtractor();

        entries.forEach(e -> {
            Set<Tag> tags = tagExtractor.extract(e.getBody());

            if(tags.isEmpty()) {
                untaggedEntriesCounter++;
            }
        });
    }

    @Override
    public String getName() {
        return NAME;
    }

    @Override
    public Object getValue() {
        return untaggedEntriesCounter;
    }
}
