package org.grizz.service;

import org.grizz.model.Statistics;
import org.grizz.service.calculators.StatisticsCalculator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.grizwold.microblog.model.Entry;

import java.util.List;

@Service
public class StatisticsProvider {
    @Autowired
    private List<StatisticsCalculator> calculators;

    public Statistics calculate(List<Entry> entries) {
        calculators.forEach(c -> c.consume(entries));
        return getStatistics(calculators);
    }

    private Statistics getStatistics(List<StatisticsCalculator> calculators) {
        Statistics stats = new Statistics();
        calculators.forEach(c -> stats.put(c.getName(), c.getValue()));
        return stats;
    }
}
