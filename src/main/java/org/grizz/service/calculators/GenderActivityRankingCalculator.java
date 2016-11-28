package org.grizz.service.calculators;

import org.grizz.model.Entry;
import org.grizz.service.calculators.structures.Ranking;
import org.grizz.service.calculators.structures.SummingRanking;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class GenderActivityRankingCalculator implements StatisticsCalculator {
    public static final String NAME = "genderActivityRanking";
    private Ranking ranking = new SummingRanking();

    @Override
    public void consume(Set<Entry> entries) {
        entries.forEach(e -> {
            ranking.add(e.getAuthorSex());
            e.getVoters().forEach(v -> ranking.add(v.getAuthorSex()));
            e.getComments().forEach(c -> {
                ranking.add(c.getAuthorSex());
                c.getVoters().forEach(v -> ranking.add(v.getAuthorSex()));
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
