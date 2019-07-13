package org.grizz.service.calculators;

import org.grizz.service.calculators.structures.Ranking;
import org.grizz.service.calculators.structures.SummingRanking;
import org.springframework.stereotype.Component;
import pl.grizwold.microblog.model.Entry;

import java.util.Set;

@Component
public class UserGroupActivityRankingCalculator implements StatisticsCalculator {
    public static final String NAME = "userGroupActivityRanking";
    private Ranking ranking = new SummingRanking();

    @Override
    public void consume(Set<Entry> entries) {
        entries.forEach(e -> {
            ranking.add(e.getAuthorGroup());
            e.getVoters().forEach(v -> ranking.add(v.getAuthorGroup()));
            e.getComments().forEach(c -> {
                ranking.add(c.getAuthorGroup());
                c.getVoters().forEach(v -> ranking.add(v.getAuthorGroup()));
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
