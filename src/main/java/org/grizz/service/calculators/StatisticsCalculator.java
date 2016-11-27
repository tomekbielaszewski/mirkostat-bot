package org.grizz.service.calculators;

import org.grizz.model.Entry;

import java.util.List;

public interface StatisticsCalculator {
    void consume(List<Entry> entries);
    String getName();
    Object getValue();
}
