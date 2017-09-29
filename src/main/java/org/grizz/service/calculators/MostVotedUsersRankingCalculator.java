package org.grizz.service.calculators;

import org.grizz.service.calculators.structures.Ranking;
import org.grizz.service.calculators.structures.SummingRanking;
import org.springframework.stereotype.Component;
import pl.grizwold.microblog.model.Entry;

import java.util.List;

@Component
public class MostVotedUsersRankingCalculator implements StatisticsCalculator {
    public static final String NAME = "mostVotedUsersRanking";
    private Ranking ranking = new SummingRanking();

    @Override
    public void consume(List<Entry> entries) {
        entries.forEach(e -> {
            ranking.add(e.getAuthor(), e.getVoteCount());
            e.getComments().forEach(c -> ranking.add(c.getAuthor(), c.getVoteCount()));
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
