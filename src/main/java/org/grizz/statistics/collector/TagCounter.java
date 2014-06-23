package org.grizz.statistics.collector;

import java.util.HashSet;
import java.util.Set;

import org.grizz.model.Entry;
import org.grizz.model.Tag;
import org.grizz.service.TagExtractor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TagCounter implements StatCollector {
	private Set<Tag> tags = new HashSet<>();
	
	@Autowired
	private TagExtractor tagExtractor;

	@Override
	public void collect(Entry entry) {
		Set<Tag> extractedTags = tagExtractor.extract(entry.getBody());
		for (Tag tag : extractedTags) {
			tags.add(tag);
		}
	}

	@Override
	public void reset() {
		tags.clear();
	}
	
	public int getNumberOfTags() {
		return tags.size();
	}

}
