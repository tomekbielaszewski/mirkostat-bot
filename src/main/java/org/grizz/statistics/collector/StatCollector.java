package org.grizz.statistics.collector;

import org.grizz.model.Entry;

public interface StatCollector {

	void collect(Entry entry);

	void reset();

}
