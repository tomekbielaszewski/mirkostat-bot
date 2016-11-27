package org.grizz.service.provider;

import org.grizz.model.Entry;

import java.util.List;

/**
 * Created by Grizz on 2014-07-03.
 */
public interface EntryProvider {
    List<Entry> getEntries();
}
