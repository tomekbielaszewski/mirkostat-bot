package org.grizz.service;

import org.grizz.model.Entry;
import org.grizz.model.Statistics;
import org.grizz.service.calculators.StatisticsCalculator;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class StatisticsProvider {
    @Autowired
    private List<StatisticsCalculator> calculators;

    public Statistics calculate(List<Entry> entries) {
        calculators.forEach(c -> c.consume(entries));
        Statistics stats = getStatistics(calculators);
        return stats;
    }

    private Statistics getStatistics(List<StatisticsCalculator> calculators) {
        Statistics stats = new Statistics();
        calculators.forEach(c -> stats.put(c.getName(), c.getValue()));
        return stats;
    }
}
