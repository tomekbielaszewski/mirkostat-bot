package org.grizz.service.calculators;

import org.springframework.stereotype.Component;
import pl.grizwold.microblog.model.Entry;

import java.util.List;

@Component
public class TotalCommentsCalculator implements StatisticsCalculator {
    public static final String NAME = "totalCommentsCounter";
    private int totalCommentsCounter = 0;

    @Override
    public void consume(List<Entry> entries) {
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
