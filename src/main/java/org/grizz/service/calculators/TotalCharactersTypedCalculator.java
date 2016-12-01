package org.grizz.service.calculators;

import org.grizz.model.Entry;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class TotalCharactersTypedCalculator implements StatisticsCalculator {
    public static final String NAME = "totalCharactersTypedCounter";
    private int totalCharactersCounter = 0;

    @Override
    public void consume(Set<Entry> entries) {
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
