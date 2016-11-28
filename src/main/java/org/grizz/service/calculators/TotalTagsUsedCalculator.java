package org.grizz.service.calculators;

import com.google.common.collect.Sets;
import org.grizz.model.Entry;
import org.grizz.model.Tag;
import org.grizz.service.calculators.structures.TagExtractor;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class TotalTagsUsedCalculator implements StatisticsCalculator {
    public static final String NAME = "totalTagsUsedCounter";
    private Set<Tag> tags = Sets.newHashSet();

    @Override
    public void consume(Set<Entry> entries) {
        TagExtractor tagExtractor = new TagExtractor();
        entries.forEach(e -> {
            tags.addAll(tagExtractor.extract(e.getBody()));
            e.getComments().forEach(c -> tags.addAll(tagExtractor.extract(c.getBody())));
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
