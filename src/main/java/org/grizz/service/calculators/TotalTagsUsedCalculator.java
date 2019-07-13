package org.grizz.service.calculators;

import com.google.common.collect.Sets;
import org.springframework.stereotype.Component;
import pl.grizwold.microblog.model.Entry;
import pl.grizwold.microblog.model.Tag;

import java.util.Set;

@Component
public class TotalTagsUsedCalculator implements StatisticsCalculator {
    public static final String NAME = "totalTagsUsedCounter";
    private Set<Tag> tags = Sets.newHashSet();

    @Override
    public void consume(Set<Entry> entries) {
        entries.forEach(e -> {
            tags.addAll(e.getTags());
            e.getComments().forEach(c -> tags.addAll(c.getTags()));
        });
    }

    @Override
    public String getName() {
        return NAME;
    }

    @Override
    public Object getValue() {
        return tags.size();
    }
}
