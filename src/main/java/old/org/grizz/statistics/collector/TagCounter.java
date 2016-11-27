package old.org.grizz.statistics.collector;

import old.org.grizz.model.Entry;
import old.org.grizz.model.EntryComment;
import old.org.grizz.model.Tag;
import old.org.grizz.service.TagExtractor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

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

        for (EntryComment entryComment : entry.getComments()) {
            Set<Tag> extractedTagsFromComment = tagExtractor.extract(entryComment.getBody());
            for (Tag tag : extractedTagsFromComment) {
                tags.add(tag);
            }
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
