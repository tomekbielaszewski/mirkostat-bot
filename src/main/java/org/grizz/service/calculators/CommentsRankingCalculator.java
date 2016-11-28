package org.grizz.service.calculators;

import org.grizz.model.Entry;
import org.grizz.service.calculators.structures.Ranking;
import org.grizz.service.calculators.structures.SimpleRanking;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class CommentsRankingCalculator implements StatisticsCalculator {
    public static final String NAME = "commentRanking";
    private Ranking ranking = new SimpleRanking();

    @Override
    public void consume(Set<Entry> entries) {
        entries.forEach(e -> e.getComments().forEach(c -> ranking.add(c, c.getVotes())));
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
