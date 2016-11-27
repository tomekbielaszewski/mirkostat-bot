package old.org.grizz.service;

import old.org.grizz.model.Entry;

/**
 * Created by Grizz on 2014-06-19.
 */
public interface EntryFactory {
    Entry[] getEntries(String json);
}
