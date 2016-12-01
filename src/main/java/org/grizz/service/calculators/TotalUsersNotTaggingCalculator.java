package org.grizz.service.calculators;

import com.google.common.collect.Sets;
import org.grizz.model.Entry;
import org.grizz.model.Tag;
import org.grizz.service.calculators.structures.Ranking;
import org.grizz.service.calculators.structures.TagExtractor;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class TotalUsersNotTaggingCalculator implements StatisticsCalculator {
    public static final String NAME = "totalUsersNotTaggingCounter";
    private Set<String> users = Sets.newHashSet();

    @Override
    public void consume(Set<Entry> entries) {
        TagExtractor tagExtractor = new TagExtractor();

        entries.forEach(e -> {
            Set<Tag> tags = tagExtractor.extract(e.getBody());

            if(tags.isEmpty()) {
                users.add(e.getAuthor());
            }
        });
    }

    @Override
    public String getName() {
        return NAME;
    }

    @Override
    public Object getValue() {
        return users.size();
    }
}
