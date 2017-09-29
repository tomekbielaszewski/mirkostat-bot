package org.grizz.service;

import org.grizz.model.Statistics;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.grizwold.microblog.model.Entry;

import java.util.List;

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

    public void run(int hoursOfHistory) {
        List<Entry> entries = entryProvider.getEntries(hoursOfHistory);
        Statistics stats = statisticsProvider.calculate(entries);
        String formattedStats = statisticsFormatter.format(stats);
        entryPoster.post(formattedStats);
    }
}
