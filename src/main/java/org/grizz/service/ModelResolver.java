package org.grizz.service;

import pl.grizwold.microblog.model.Entry;

import java.util.HashMap;
import java.util.Map;

public class ModelResolver {
    private final static Map<String, Class> resourceModelAssociation = new HashMap<>();

    static {
        resourceModelAssociation.put("stream", Entry.class);
    }

    public <T> Class<T> resolve(String resource) {
        return resourceModelAssociation.get(resource.toLowerCase());
    }
}
