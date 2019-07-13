package org.grizz.service.calculators;

import org.grizz.service.calculators.structures.Ranking;
import org.grizz.service.calculators.structures.SummingRanking;
import org.springframework.stereotype.Component;
import pl.grizwold.microblog.model.Entry;

import java.util.Set;

@Component
public class TagUsageRankingCalculator implements StatisticsCalculator {
    public static final String NAME = "tagUsageRanking";
    private Ranking ranking = new SummingRanking();

    @Override
    public void consume(Set<Entry> entries) {
        entries.forEach(e -> {
            e.getTags().forEach(t -> ranking.add(t.getName()));
            e.getComments().forEach(c -> c.getTags().forEach(t -> ranking.add(t.getName())));
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
