package org.grizz.service.calculators;

import org.apache.commons.lang3.StringUtils;
import org.grizz.service.calculators.structures.Ranking;
import org.grizz.service.calculators.structures.SummingRanking;
import org.springframework.stereotype.Component;
import pl.grizwold.microblog.model.Entry;

import java.util.Set;

@Component
public class UserAppRankingCalculator implements StatisticsCalculator {
    public static final String NAME = "userAppRanking";
    private Ranking ranking = new SummingRanking();

    @Override
    public void consume(Set<Entry> entries) {
        entries.forEach(e -> {
            count(e.getApp());
            e.getComments().forEach(c -> count(c.getApp()));
        });
    }

    private void count(String app) {
        if (StringUtils.isBlank(app))
            return;
        ranking.add(app);
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
