package org.grizz.service.calculators;

import org.grizz.model.Entry;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TotalVotesCalculator implements StatisticsCalculator {
    public static final String NAME = "totalVotesCounter";
    private int totalVotesCounter = 0;

    @Override
    public void consume(List<Entry> entries) {
        entries.forEach(e -> {
            totalVotesCounter += e.getVotes();
            e.getComments().forEach(c -> totalVotesCounter += c.getVotes());
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
