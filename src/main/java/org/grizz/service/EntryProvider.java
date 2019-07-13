package org.grizz.service;

import com.google.common.collect.Lists;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.extern.slf4j.Slf4j;
import org.grizz.model.Entry;
import org.grizz.session.ClientProvider;
import org.springframework.stereotype.Service;
import pl.grizwold.wykop.WykopClient;
import pl.grizwold.wykop.resources.entries.EntriesStream;

import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Service
public class EntryProvider {
    private static final long DAY = 24 * 60 * 60 * 1000;
    private static final Integer FIRST_PAGE = 1;

    private final ClientProvider clientProvider;
    private Gson gson = new GsonBuilder()
            .setDateFormat("yyyy-MM-dd HH:mm:ss")
            .create();

    public EntryProvider(ClientProvider clientProvider) {
        this.clientProvider = clientProvider;
    }

    public Set<Entry> getEntries() {
        WykopClient session = clientProvider.getReaderSession();
        List<Entry> allEntries = Lists.newArrayList();
        allEntries.addAll(getFirstPage(session));
        Long lastSeenId = allEntries.get(allEntries.size() - 1).getId();
        allEntries.addAll(getNextPages(lastSeenId, session));
        return new HashSet<>(allEntries);
    }

    private List<Entry> getFirstPage(WykopClient session) {
        String firstPage = EntriesStream.builder()
                .page(FIRST_PAGE)
                .build()
                .call(session)
                .getJson();
        return jsonToEntries(firstPage);
    }

    private List<Entry> getNextPages(Long id, WykopClient session) {
        String json = EntriesStream.builder()
                .firstId(id)
                .build()
                .call(session)
                .getJson();
        List<Entry> entries = jsonToEntries(json).stream()
                .filter(this::isOldEntry)
                .sorted(Comparator.comparing(Entry::getDateAdded))
                .collect(Collectors.toList());

        if (!entries.isEmpty()) {
            Long lastEntryId = entries.get(entries.size() - 1).getId();
            entries.addAll(getNextPages(lastEntryId, session));
        }

        return entries;
    }

    private boolean isOldEntry(Entry e) {
        return System.currentTimeMillis() - DAY < e.getDateAdded().getTime();
    }

    private List<Entry> jsonToEntries(String json) {
        Entry[] entries = gson.fromJson(json, Entry[].class);
        return Arrays.asList(entries);
    }
}
