package org.grizz.service;

import com.google.gson.Gson;
import org.grizz.model.Entry;
import org.springframework.stereotype.Service;

/**
 * Created by Grizz on 2014-06-19.
 */
@Service
public class EntryFactoryImpl implements EntryFactory {
    @Override
    public Entry[] getEntries(String json) {
        Gson gson = new Gson();
        Entry[] entries = gson.fromJson(json, Entry[].class);
        return entries;
    }
}
