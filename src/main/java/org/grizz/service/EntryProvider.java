package org.grizz.service;

import com.crozin.wykop.sdk.Command;
import com.google.common.collect.FluentIterable;
import com.google.common.collect.Lists;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.extern.slf4j.Slf4j;
import org.grizz.model.Entry;
import org.grizz.session.Session;
import org.grizz.session.SessionProvider;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Service
public class EntryProvider {
    private final long DAY = 24 * 60 * 60 * 1000;

    private final SessionProvider sessionProvider;
    private Gson gson = new GsonBuilder()
            .setDateFormat("yyyy-MM-dd HH:mm:ss")
            .create();

    public EntryProvider(SessionProvider sessionProvider) {
        this.sessionProvider = sessionProvider;
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
                .filter(this::isOldEntry)
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
        log.info(entries[entries.length - 1].getDateAdded().toString());
        return Lists.newArrayList(entries);
    }

    private Command getStreamIndexCommand() {
        Command command = new Command("stream", "index", "0");
        command.setClear(true);
        return command;
    }

    private Command getStreamFirstIdCommand(String id) {
        Command command = new Command("stream", "firstid", id);
        command.setClear(true);
        return command;
    }
}
