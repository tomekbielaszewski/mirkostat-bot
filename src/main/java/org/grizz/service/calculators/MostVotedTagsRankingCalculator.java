package org.grizz.service.calculators;

import org.grizz.service.calculators.structures.Ranking;
import org.grizz.service.calculators.structures.SummingRanking;
import org.springframework.stereotype.Component;
import pl.grizwold.microblog.model.Entry;

import java.util.List;

@Component
public class MostVotedTagsRankingCalculator implements StatisticsCalculator {
    public static final String NAME = "mostVotedTagsRanking";
    private Ranking ranking = new SummingRanking();

    @Override
    public void consume(List<Entry> entries) {
        entries.forEach(e -> {
            e.getTags().forEach(t -> ranking.add(t.getName(), e.getVoteCount()));
            e.getComments().forEach(c -> c.getTags().forEach(t -> ranking.add(t.getName(), c.getVoteCount())));
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
