package org.grizz.service.calculators;

import org.apache.commons.lang3.StringUtils;
import org.grizz.model.Entry;
import org.grizz.service.calculators.structures.Ranking;
import org.grizz.service.calculators.structures.SummingRanking;
import org.springframework.stereotype.Component;

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

    private void count(String gender) {
        if (StringUtils.isBlank(gender))
            return;
        ranking.add(gender);
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
