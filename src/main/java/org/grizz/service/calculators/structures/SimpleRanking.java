package org.grizz.service.calculators.structures;

import com.google.common.collect.Lists;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Ranking which can contain duplicated objects mapped to different values
 */
public class SimpleRanking implements Ranking {
    private List<RankedObject> ranking = Lists.newArrayList();

    @Override
    public void add(Object obj) {
        add(obj, 1);
    }

    @Override
    public void add(Object obj, int value) {
        ranking.add(new RankedObject(obj, value));
    }

    @Override
    public List<RankedObject> asList() {
        return ranking.stream().sorted().collect(Collectors.toList());
    }
}
