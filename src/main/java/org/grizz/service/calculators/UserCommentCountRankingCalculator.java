package org.grizz.service.calculators;

import org.grizz.model.Entry;
import org.grizz.service.calculators.structures.Ranking;
import org.grizz.service.calculators.structures.SummingRanking;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class UserCommentCountRankingCalculator implements StatisticsCalculator {
    public static final String NAME = "userCommentCountRanking";
    private Ranking ranking = new SummingRanking();

    @Override
    public void consume(Set<Entry> entries) {
        entries.forEach(e -> {
            e.getComments().forEach(c -> {
                ranking.add(c.getAuthor());
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
