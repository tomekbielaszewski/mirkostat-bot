package org.grizz.service.calculators;

import com.google.common.collect.Sets;
import org.grizz.model.Entry;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class UserCounterCalculator implements StatisticsCalculator {
    public static final String NAME = "userCounter";
    private Set<String> users = Sets.newHashSet();

    @Override
    public void consume(Set<Entry> entries) {
        entries.forEach(e -> {
            users.add(e.getAuthor());
            e.getVoters().forEach(v -> users.add(v.getAuthor()));
            e.getComments().forEach(c -> {
                users.add(c.getAuthor());
                c.getVoters().forEach(v -> users.add(v.getAuthor()));
            });
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
