package org.grizz.service.calculators;

import org.grizz.service.calculators.structures.Ranking;
import org.grizz.service.calculators.structures.SimpleRanking;
import org.springframework.stereotype.Component;
import pl.grizwold.microblog.model.Entry;

import java.util.List;

@Component
public class CommentsRankingCalculator implements StatisticsCalculator {
    public static final String NAME = "commentsRanking";
    private Ranking ranking = new SimpleRanking();

    @Override
    public void consume(List<Entry> entries) {
        entries.forEach(e -> e.getComments().forEach(c -> ranking.add(c, c.getVoteCount())));
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
