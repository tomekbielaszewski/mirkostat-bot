package old.org.grizz.statistics;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import old.org.grizz.model.Tag;
import org.springframework.stereotype.Service;

@Service
public class TagStats {
	/**
	 * licznik wystapien tagu we wpisach. 
	 */
	private Map<String, Integer> tagCounter = new HashMap<>();
	
	/**
	 * zmapowanie sily relacji do tagu ktorego ta sila dotyczy
	 */
	private Map<String, RelationStrength> tagRelationStrength = new HashMap<>();
	
	public void update(Set<Tag> tags) {
		updateCounter(tags);
		updateRelationStrength(tags);
	}
	
	public void reset() {
		tagCounter.clear();
		tagRelationStrength.clear();
	}
	
	public Map<String, Integer> getRelationStrength(String tag) {
		return tagRelationStrength.get(tag).getRelationStrength();
	}
	
	public Integer getCount(String tag) {
		return tagCounter.get(tag);
	}

	private void updateCounter(Set<Tag> tags) {
		for (Tag tag : tags) {
			String tagName = tag.getName();
			if(tagCounter.containsKey(tagName)) {
				Integer count = tagCounter.get(tagName);
				count++;
				tagCounter.put(tagName, count);
			} else {
				tagCounter.put(tagName, 1);
			}
		}
	}
	
	private void updateRelationStrength(Set<Tag> tags) {
		for (Tag tag : tags) {
			String tagName = tag.getName();
			if(tagRelationStrength.containsKey(tagName)) {
				RelationStrength relation = tagRelationStrength.get(tagName);
				relation.update(tags);
			} else {
				RelationStrength relation = new RelationStrength(tagName);
				tagRelationStrength.put(tagName, relation);
				relation.update(tags);
			}
		}
	}

	/**
	 * Sila relacji to liczba wystapien konkretnego tagu przy tagu zmapowanym do tej relacji na przestrzeni wielu wpisow mikrobloga.
	 * @author Tomek
	 *
	 */
	private class RelationStrength {
		private Map<String, Integer> relationStrength = new HashMap<>();
		private String mappedTag;
		
		public RelationStrength(String mappedTag) {
			this.mappedTag = mappedTag;
		}
		
		public Map<String, Integer> getRelationStrength() {
			return this.relationStrength;
		}

		public void update(Set<Tag> tags) {
			for (Tag tag : tags) {
				String tagName = tag.getName();
				if(!tagName.equals(mappedTag)) {
					if(relationStrength.containsKey(tagName)) {
						Integer count = relationStrength.get(tagName);
						count++;
						relationStrength.put(tagName, count);
					} else {
						relationStrength.put(tagName, 1);
					}
				}
			}
		}
	}
}
