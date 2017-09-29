package org.grizz.service.calculators;

import org.grizz.service.calculators.structures.Ranking;
import org.grizz.service.calculators.structures.SummingRanking;
import org.springframework.stereotype.Component;
import pl.grizwold.microblog.model.Entry;

import java.util.List;

@Component
public class CharactersTypedPerUserRankingCalculator implements StatisticsCalculator {
    public static final String NAME = "charactersTypedPerUserRanking";
    private Ranking ranking = new SummingRanking();

    @Override
    public void consume(List<Entry> entries) {
        entries.forEach(e -> {
            ranking.add(e.getAuthor(), e.getBody().length());
            e.getComments().forEach(c -> ranking.add(c.getAuthor(), c.getBody().length()));
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
