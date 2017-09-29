package org.grizz.service.calculators;

import org.grizz.service.calculators.structures.Ranking;
import org.grizz.service.calculators.structures.SummingRanking;
import org.springframework.stereotype.Component;
import pl.grizwold.microblog.model.Entry;

import java.util.List;

@Component
public class UserCommentCountRankingCalculator implements StatisticsCalculator {
    public static final String NAME = "userCommentCountRanking";
    private Ranking ranking = new SummingRanking();

    @Override
    public void consume(List<Entry> entries) {
        entries.forEach(e -> e.getComments().forEach(c -> ranking.add(c.getAuthor())));
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
