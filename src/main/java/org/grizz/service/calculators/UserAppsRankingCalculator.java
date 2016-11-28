package org.grizz.service.calculators;

import org.grizz.model.Entry;
import org.grizz.model.Tag;
import org.grizz.service.calculators.structures.Ranking;
import org.grizz.service.calculators.structures.SummingRanking;
import org.grizz.service.calculators.structures.TagExtractor;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class UserAppsRankingCalculator implements StatisticsCalculator {
    public static final String NAME = "userAppsRanking";
    private Ranking ranking = new SummingRanking();

    @Override
    public void consume(Set<Entry> entries) {
        entries.forEach(e -> {
            ranking.add(e.getApp());
            e.getComments().forEach(c -> ranking.add(c.getApp()));
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
