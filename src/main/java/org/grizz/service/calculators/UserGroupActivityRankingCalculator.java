package org.grizz.service.calculators;

import org.grizz.model.Entry;
import org.grizz.model.UserGroup;
import org.grizz.service.calculators.structures.Ranking;
import org.grizz.service.calculators.structures.SimpleRanking;
import org.grizz.service.calculators.structures.SummingRanking;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class UserGroupActivityRankingCalculator implements StatisticsCalculator {
    public static final String NAME = "userGroupActivityRanking";
    private Ranking ranking = new SummingRanking();

    @Override
    public void consume(Set<Entry> entries) {
        entries.forEach(e -> {
            count(e.getAuthorGroup());
            e.getVoters().forEach(v -> count(v.getAuthorGroup()));
            e.getComments().forEach(c -> {
                count(c.getAuthorGroup());
                c.getVoters().forEach(v -> count(v.getAuthorGroup()));
            });
        });
    }

    private void count(int authorGroup) {
        ranking.add(UserGroup.byValue(authorGroup));
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
