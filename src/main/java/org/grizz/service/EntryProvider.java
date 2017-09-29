package org.grizz.service;

import lombok.extern.slf4j.Slf4j;
import org.grizz.model.EntryRepository;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.grizwold.microblog.model.Entry;

import java.util.List;

@Slf4j
@Service
public class EntryProvider {
    @Autowired
    private EntryRepository entryRepository;

    public List<Entry> getEntries(int hoursOfHistory) {
        DateTime dateSince = DateTime.now().minusHours(hoursOfHistory);
        return entryRepository.findByDateAddedGreaterThan(dateSince);
    }
}
