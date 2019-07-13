package org.grizz.service;

import org.grizz.model.Entry;
import org.grizz.model.Statistics;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class Task {
    private final EntryProvider entryProvider;
    private final StatisticsProvider statisticsProvider;
    private final StatisticsFormatter statisticsFormatter;
    private final EntryPoster entryPoster;

    public Task(EntryProvider entryProvider, StatisticsProvider statisticsProvider, StatisticsFormatter statisticsFormatter, EntryPoster entryPoster) {
        this.entryProvider = entryProvider;
        this.statisticsProvider = statisticsProvider;
        this.statisticsFormatter = statisticsFormatter;
        this.entryPoster = entryPoster;
    }

    public void run() {
        Set<Entry> entries = entryProvider.getEntries();
        Statistics stats = statisticsProvider.calculate(entries);
        String formattedStats = statisticsFormatter.format(stats);
        entryPoster.post(formattedStats);
    }
}
