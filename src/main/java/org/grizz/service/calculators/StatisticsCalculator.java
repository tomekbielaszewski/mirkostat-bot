package org.grizz.service.calculators;

import pl.grizwold.microblog.model.Entry;

import java.util.Set;

public interface StatisticsCalculator {
    void consume(Set<Entry> entries);

    String getName();

    Object getValue();
}
