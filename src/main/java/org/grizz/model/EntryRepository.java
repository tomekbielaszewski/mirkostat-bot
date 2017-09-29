package org.grizz.model;

import org.joda.time.DateTime;
import org.springframework.data.mongodb.repository.MongoRepository;
import pl.grizwold.microblog.model.Entry;

import java.util.List;

public interface EntryRepository extends MongoRepository<Entry, Long> {
    List<Entry> findByDateAddedGreaterThan(DateTime dateAdded);
}
