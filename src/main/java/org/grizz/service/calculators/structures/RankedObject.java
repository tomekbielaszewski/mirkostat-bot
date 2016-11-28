package org.grizz.service.calculators.structures;

import lombok.Value;

@Value
public class RankedObject implements Comparable {
    private Object key;
    private int value;

    @Override
    public int compareTo(Object o) {
        if(!(o instanceof RankedObject))
            return 0;
        return ((RankedObject)o).getValue() - this.getValue();
    }
}
