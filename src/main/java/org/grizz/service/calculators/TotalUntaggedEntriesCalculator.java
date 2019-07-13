package org.grizz.service.calculators;

import org.springframework.stereotype.Component;
import pl.grizwold.microblog.model.Entry;
import pl.grizwold.microblog.model.Tag;

import java.util.Set;

@Component
public class TotalUntaggedEntriesCalculator implements StatisticsCalculator {
    public static final String NAME = "untaggedEntriesCounter";
    private int untaggedEntriesCounter = 0;

    @Override
    public void consume(Set<Entry> entries) {
        entries.forEach(e -> {
            Set<Tag> tags = e.getTags();

            if (tags.isEmpty()) {
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
