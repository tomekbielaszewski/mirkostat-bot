package org.grizz.service.calculators.structures;

import com.google.common.collect.Sets;
import org.grizz.model.Tag;

import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TagExtractor {

    public Set<Tag> extract(String body) {
        Set<Tag> tags = Sets.newHashSet();
        Pattern p = Pattern.compile("(\\s|^)#\\w{2,}", Pattern.CASE_INSENSITIVE);
        Matcher m = p.matcher(body);

        while (m.find()) {
            tags.add(Tag.builder().name(m.group().trim()).build());
        }

        return tags;
    }
}
