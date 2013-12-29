package org.grizz.statistics.collector;

import java.util.Set;

import org.grizz.model.Entry;
import org.grizz.model.Tag;
import org.grizz.service.TagExtractor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TagRateCounter extends AbstractStatCounter {
	@Autowired
	private TagExtractor tagExtractor;
	
	@Override
	public void collect(Entry entry) {
		Set<Tag> tags = tagExtractor.extract(entry.getBody());
		for (Tag tag : tags) {
			addToCounter(tag.getName(), 1);
		}
	}
	
	public int getHighestTagRate() {
		return getEntryWithHighestCount().getValue();
	}
	
	public String getTagWithHighestRate() {
		return (String) getEntryWithHighestCount().getKey();
	}
}
