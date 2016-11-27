package old.org.grizz.statistics.collector;

import old.org.grizz.model.Entry;

public interface StatCollector {

	void collect(Entry entry);

	void reset();

}
