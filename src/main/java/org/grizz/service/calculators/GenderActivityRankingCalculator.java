package org.grizz.service.calculators;

import org.grizz.service.calculators.structures.Ranking;
import org.grizz.service.calculators.structures.SummingRanking;
import org.springframework.stereotype.Component;
import pl.grizwold.microblog.model.Entry;
import pl.grizwold.microblog.model.UserSex;

import java.util.Optional;
import java.util.Set;

@Component
public class GenderActivityRankingCalculator implements StatisticsCalculator {
    public static final String NAME = "genderActivityRanking";
    private Ranking ranking = new SummingRanking();

    @Override
    public void consume(Set<Entry> entries) {
        entries.forEach(e -> {
            count(e.getAuthorSex());
            e.getVoters().forEach(v -> count(v.getAuthorSex()));
            e.getComments().forEach(c -> {
                count(c.getAuthorSex());
                c.getVoters().forEach(v -> count(v.getAuthorSex()));
            });
        });
    }

    private void count(UserSex gender) {
        Optional.ofNullable(gender)
                .ifPresent(g -> ranking.add(gender));
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
