package org.grizz.service.calculators;

import org.grizz.service.calculators.structures.Ranking;
import org.grizz.service.calculators.structures.SummingRanking;
import org.springframework.stereotype.Component;
import pl.grizwold.microblog.model.Entry;

import java.util.List;

@Component
public class UserActivityRankingCalculator implements StatisticsCalculator {
    public static final String NAME = "userActivityRanking";
    private Ranking ranking = new SummingRanking();

    @Override
    public void consume(List<Entry> entries) {
        entries.forEach(e -> {
            ranking.add(e.getAuthor());
            e.getVoters().forEach(v -> ranking.add(v.getAuthor()));
            e.getComments().forEach(c -> {
                ranking.add(c.getAuthor());
                c.getVoters().forEach(v -> ranking.add(v.getAuthor()));
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
