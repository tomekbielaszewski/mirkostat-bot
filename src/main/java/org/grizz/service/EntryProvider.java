package org.grizz.service;

import com.crozin.wykop.sdk.Command;
import com.google.common.collect.FluentIterable;
import com.google.common.collect.Lists;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.grizz.model.Entry;
import org.grizz.session.Session;
import org.grizz.session.SessionProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class EntryProvider {
    private final long DAY = 24 * 60 * 60 * 1000;

    @Autowired
    private SessionProvider sessionProvider;
    private Gson gson = new GsonBuilder()
            .setDateFormat("yyyy-MM-dd HH:mm:ss")
            .create();

    public Set<Entry> getDevEntries() {
        try {
            return Files.readAllLines(Paths.get("testEntries.json"), Charset.defaultCharset()).stream()
                    .map(line -> gson.fromJson(line, Entry[].class))
                    .flatMap(Arrays::stream)
                    .collect(Collectors.toSet());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Set<Entry> getEntries() {
        Session session = sessionProvider.getGetterSession();
        List<Entry> allEntries = Lists.newArrayList();
        allEntries.addAll(getFirstPage(session));
        allEntries.addAll(getNextPages(allEntries.get(allEntries.size() - 1).getId(), session));
        return FluentIterable.from(allEntries).toSet();
    }

    private List<Entry> getFirstPage(Session session) {
        String firstPage = session.execute(getStreamIndexCommand());
        Entry[] entries = gson.fromJson(firstPage, Entry[].class);
        return Lists.newArrayList(entries);
    }

    private List<Entry> getNextPages(Long id, Session session) {
        List<Entry> nextPage = getNextPage(id, session).stream()
                .filter(e -> isOldEntry(e))
                .collect(Collectors.toList());
        if (!nextPage.isEmpty()) {
            Entry lastEntry = nextPage.get(nextPage.size() - 1);
            nextPage.addAll(getNextPages(lastEntry.getId(), session));
        }
        return nextPage;
    }

    private boolean isOldEntry(Entry e) {
        return System.currentTimeMillis() - DAY < e.getDateAdded().getTime();
    }

    private List<Entry> getNextPage(Long id, Session session) {
        String firstPage = session.execute(getStreamFirstIdCommand(id.toString()));
        Entry[] entries = gson.fromJson(firstPage, Entry[].class);
        return Lists.newArrayList(entries);
    }

    private Command getStreamIndexCommand() {
        return new Command("stream", "index", "0");
    }

    private Command getStreamFirstIdCommand(String id) {
        return new Command("stream", "firstid", id);
    }
}
