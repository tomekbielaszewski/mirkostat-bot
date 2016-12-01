package org.grizz.service.calculators;

import com.google.common.collect.Sets;
import org.apache.commons.lang3.StringUtils;
import org.grizz.model.Entry;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class TotalUserAppsCalculator implements StatisticsCalculator {
    public static final String NAME = "totalUserAppsCounter";
    private Set<String> apps = Sets.newHashSet();

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
        apps.add(app);
    }

    @Override
    public String getName() {
        return NAME;
    }

    @Override
    public Object getValue() {
        return apps.size();
    }
}
