package old.org.grizz.service;

import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import old.org.grizz.model.Tag;
import org.springframework.stereotype.Service;

@Service
public class TagExtractor {

	public Set<Tag> extract(String body) {
		Set<Tag> tags = new HashSet<>();
		Pattern p = Pattern.compile("#\\w{2,}", Pattern.CASE_INSENSITIVE);
		Matcher m = p.matcher(body);
		
		while(m.find()){
			Tag tag = new Tag();
			tag.setName(m.group());
			
			tags.add(tag);
		}
		
		return tags;
	}
}
