package org.grizz.service.calculators;

import org.grizz.model.Entry;
import org.grizz.service.calculators.structures.Ranking;
import org.grizz.service.calculators.structures.SummingRanking;
import org.grizz.service.calculators.structures.TagExtractor;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class TagUsageRankingCalculator implements StatisticsCalculator {
    public static final String NAME = "tagUsageRanking";
    private Ranking ranking = new SummingRanking();

    @Override
    public void consume(Set<Entry> entries) {
        TagExtractor tagExtractor = new TagExtractor();
        entries.forEach(e -> {
            tagExtractor.extract(e.getBody()).forEach(t -> ranking.add(t.getName()));
            e.getComments().forEach(c -> tagExtractor.extract(c.getBody()).forEach(t -> ranking.add(t.getName())));
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
