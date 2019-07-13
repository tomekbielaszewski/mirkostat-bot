package org.grizz.service;

import org.grizz.config.Configuration;
import org.grizz.model.Entry;
import org.grizz.model.Statistics;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class Task {
    @Autowired
    private EntryProvider entryProvider;
    @Autowired
    private StatisticsProvider statisticsProvider;
    @Autowired
    private StatisticsFormatter statisticsFormatter;
    @Autowired
    private EntryPoster entryPoster;

    public void run(Configuration configuration) {
        Set<Entry> entries = entryProvider.getEntries(configuration);
        Statistics stats = statisticsProvider.calculate(entries);
        String formattedStats = statisticsFormatter.format(stats);
        entryPoster.post(formattedStats);
    }
}
