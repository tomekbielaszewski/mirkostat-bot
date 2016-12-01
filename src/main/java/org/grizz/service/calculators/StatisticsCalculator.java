package org.grizz.service.calculators;

import org.grizz.model.Entry;

import java.util.Set;

public interface StatisticsCalculator {
    void consume(Set<Entry> entries);

    String getName();

    Object getValue();
}
