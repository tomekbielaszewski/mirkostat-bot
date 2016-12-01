package org.grizz.service.calculators;

import org.grizz.model.Entry;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class TotalCommentsCalculator implements StatisticsCalculator {
    public static final String NAME = "totalCommentsCounter";
    private int totalCommentsCounter = 0;

    @Override
    public void consume(Set<Entry> entries) {
        entries.forEach(e -> totalCommentsCounter += e.getComments().size());
    }

    @Override
    public String getName() {
        return NAME;
    }

    @Override
    public Object getValue() {
        return totalCommentsCounter;
    }
}
