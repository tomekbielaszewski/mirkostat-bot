package org.grizz.service;

import lombok.extern.log4j.Log4j;
import org.grizz.model.Statistics;
import org.grizz.service.calculators.StatisticsCalculator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.grizwold.microblog.model.Entry;

import java.util.List;

@Service
@Log4j
public class StatisticsProvider {
    @Autowired
    private List<StatisticsCalculator> calculators;

    public Statistics calculate(List<Entry> entries) {
        calculators.forEach(c -> {
            log.info("Calculating " + c.getName());
            c.consume(entries);
        });
        return getStatistics(calculators);
    }

    private Statistics getStatistics(List<StatisticsCalculator> calculators) {
        Statistics stats = new Statistics();
        calculators.forEach(c -> stats.put(c.getName(), c.getValue()));
        return stats;
    }
}
