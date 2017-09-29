package org.grizz.service.calculators;

import org.springframework.stereotype.Component;
import pl.grizwold.microblog.model.Entry;

import java.util.List;

@Component
public class TotalVotesCalculator implements StatisticsCalculator {
    public static final String NAME = "totalVotesCounter";
    private int totalVotesCounter = 0;

    @Override
    public void consume(List<Entry> entries) {
        entries.forEach(e -> {
            totalVotesCounter += e.getVoteCount();
            e.getComments().forEach(c -> totalVotesCounter += c.getVoteCount());
        });
    }

    @Override
    public String getName() {
        return NAME;
    }

    @Override
    public Object getValue() {
        return totalVotesCounter;
    }
}
