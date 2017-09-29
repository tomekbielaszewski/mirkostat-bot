package org.grizz.service.calculators;

import com.google.common.collect.Sets;
import org.springframework.stereotype.Component;
import pl.grizwold.microblog.model.Entry;
import pl.grizwold.microblog.model.Tag;

import java.util.List;
import java.util.Set;

@Component
public class TotalUsersNotTaggingCalculator implements StatisticsCalculator {
    public static final String NAME = "totalUsersNotTaggingCounter";
    private Set<String> users = Sets.newHashSet();

    @Override
    public void consume(List<Entry> entries) {
        entries.forEach(e -> {
            Set<Tag> tags = e.getTags();

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
