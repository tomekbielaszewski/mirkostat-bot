package org.grizz.service.calculators;

import org.springframework.stereotype.Component;
import pl.grizwold.microblog.model.Entry;

import java.util.List;

@Component
public class TotalCharactersTypedCalculator implements StatisticsCalculator {
    public static final String NAME = "totalCharactersTypedCounter";
    private int totalCharactersCounter = 0;

    @Override
    public void consume(List<Entry> entries) {
        entries.forEach(e -> {
            totalCharactersCounter += e.getBody().length();
            e.getComments().forEach(c -> totalCharactersCounter += c.getBody().length());
        });
    }

    @Override
    public String getName() {
        return NAME;
    }

    @Override
    public Object getValue() {
        return totalCharactersCounter;
    }
}
