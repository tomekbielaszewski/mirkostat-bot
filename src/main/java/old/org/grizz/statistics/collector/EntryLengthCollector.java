package old.org.grizz.statistics.collector;

import old.org.grizz.model.Entry;
import old.org.grizz.model.EntryComment;
import org.springframework.stereotype.Service;

@Service
public class EntryLengthCollector implements StatCollector {
	private int overallLength;

	@Override
	public void collect(Entry entry) {
		overallLength += entry.getBody().length();

        for (EntryComment entryComment : entry.getComments()) {
            overallLength += entryComment.getBody().length();
        }
    }

	@Override
	public void reset() {
		overallLength = 0;
	}
	
	public int getOverallLength() {
		return overallLength;
	}
}
