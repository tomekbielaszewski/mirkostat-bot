package org.grizz.service.provider;

import org.grizz.model.Entry;
import org.grizz.service.EntryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by Grizz on 2014-07-03.
 */
public class DevEntryProvider implements EntryProvider {
    @Autowired
    ApplicationContext ctx;
    @Autowired
    private EntryFactory entryFactory;

    @Override
    public List<Entry> getEntries() {
        List<String> jsonLines = Collections.emptyList();
        List<Entry> entries = new ArrayList<>();
        int counter = 1;

        System.out.println("Reading file...");

        try {
            jsonLines = Files.readAllLines(Paths.get("testEntries.json"), Charset.defaultCharset());
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (String jsonLine : jsonLines) {
            System.out.println("Building entries... page " + (counter++));

            Entry[] entriesArr = entryFactory.getEntries(jsonLine);
            entries.addAll(Arrays.asList(entriesArr));
        }

        System.out.println("Loaded!\n");

        return entries;
    }


}
