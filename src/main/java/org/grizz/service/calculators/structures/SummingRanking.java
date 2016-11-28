package org.grizz.service.calculators.structures;

import com.google.common.collect.Maps;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class SummingRanking implements Ranking {
    private Map<Object, Integer> ranking = Maps.newHashMap();

    @Override
    public void add(Object obj, int value) {
        if (ranking.containsKey(obj)) {
            Integer oldValue = ranking.get(obj);
            value += oldValue;
        }
        ranking.put(obj, value);
    }

    @Override
    public List<RankedObject> asList() {
        return ranking.entrySet().stream()
                .map(e -> new RankedObject(e.getKey(), e.getValue()))
                .sorted()
                .collect(Collectors.toList());
    }
}
