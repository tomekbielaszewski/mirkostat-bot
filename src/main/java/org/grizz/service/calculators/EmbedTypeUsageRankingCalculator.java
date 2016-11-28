package org.grizz.service.calculators;

import org.grizz.model.Embed;
import org.grizz.model.Entry;
import org.grizz.service.calculators.structures.Ranking;
import org.grizz.service.calculators.structures.SummingRanking;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.Set;

@Component
public class EmbedTypeUsageRankingCalculator implements StatisticsCalculator {
    public static final String NAME = "embedTypeUsageRanking";
    private Ranking ranking = new SummingRanking();

    @Override
    public void consume(Set<Entry> entries) {
        entries.forEach(e -> {
            count(e.getEmbed());
            e.getComments().forEach(comment -> count(comment.getEmbed()));
        });
    }

    private void count(Embed embed) {
        Optional.ofNullable(embed).ifPresent(e -> ranking.add(e.getType()));
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
