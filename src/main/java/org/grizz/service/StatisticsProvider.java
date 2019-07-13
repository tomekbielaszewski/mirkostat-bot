package org.grizz.service;

import org.grizz.model.Entry;
import org.grizz.model.Statistics;
import org.grizz.service.calculators.StatisticsCalculator;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class StatisticsProvider {
    private final List<StatisticsCalculator> calculators;

    public StatisticsProvider(List<StatisticsCalculator> calculators) {
        this.calculators = calculators;
    }

    public Statistics calculate(Set<Entry> entries) {
        calculators.forEach(c -> c.consume(entries));
        return getStatistics(calculators);
    }

    private Statistics getStatistics(List<StatisticsCalculator> calculators) {
        Statistics stats = new Statistics();
        calculators.forEach(c -> stats.put(c.getName(), c.getValue()));
        return stats;
    }
}
