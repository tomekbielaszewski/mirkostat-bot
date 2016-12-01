package org.grizz.service.calculators.structures;

import java.util.List;

public interface Ranking {
    void add(Object obj);
    void add(Object obj, int value);
    List<RankedObject> asList();
}
