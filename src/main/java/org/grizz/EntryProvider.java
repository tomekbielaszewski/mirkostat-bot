package org.grizz;

import org.grizz.model.Entry;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class EntryProvider {
    private final long DAY = 24 * 60 * 60 * 1000;

    public List<Entry> getList() {
        return Collections.emptyList();
    }
}
