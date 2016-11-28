package org.grizz.service.calculators;

import org.grizz.model.Entry;
import org.grizz.service.calculators.structures.Ranking;
import org.grizz.service.calculators.structures.SummingRanking;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class CharactersTypedPerUserRankingCalculator implements StatisticsCalculator {
    public static final String NAME = "charactersTypedPerUserRanking";
    private Ranking ranking = new SummingRanking();

    @Override
    public void consume(Set<Entry> entries) {
        entries.forEach(e -> {
            ranking.add(e.getAuthor(), e.getBody().length());
            e.getComments().forEach(c -> {
                ranking.add(c.getAuthor(), c.getBody().length());
            });
        });
    }

    @Override
    public String getName() {
        return NAME;
    }

    @Override
    public Object getValue() {
        return ranking.asList();
    }
}
