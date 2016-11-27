package org.grizz.model;

import java.util.HashMap;
import java.util.Map;

public class Statistics {
    private Map<String, Object> stats = new HashMap<>();

    public void put(String name, Object value) {
        stats.put(name, value);
    }

    public Map<String, Object> getStats() {
        return stats;
    }
}
