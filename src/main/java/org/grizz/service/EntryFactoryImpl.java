package org.grizz.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.grizz.model.Entry;
import org.springframework.stereotype.Service;

/**
 * Created by Grizz on 2014-06-19.
 */
@Service
public class EntryFactoryImpl implements EntryFactory {
    @Override
    public Entry[] getEntries(String json) {
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd HH:mm:ss") //"date":"2014-11-19 23:55:21",
                .create();
        Entry[] entries = gson.fromJson(json, Entry[].class);
        return entries;
    }
}
