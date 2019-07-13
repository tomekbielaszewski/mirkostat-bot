package org.grizz.service.calculators;

import org.springframework.stereotype.Component;
import pl.grizwold.microblog.model.Entry;

import java.util.Set;

@Component
public class TotalEntriesCalculator implements StatisticsCalculator {
    public static final String NAME = "totalEntriesCounter";
    private int totalEntriesCounter = 0;

    @Override
    public void consume(Set<Entry> entries) {
        totalEntriesCounter += entries.size();
    }

    @Override
    public String getName() {
        return NAME;
    }

    @Override
    public Object getValue() {
        return totalEntriesCounter;
    }
}
