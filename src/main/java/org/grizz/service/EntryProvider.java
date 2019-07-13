package org.grizz.service;

import com.crozin.wykop.sdk.Command;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.extern.slf4j.Slf4j;
import org.grizz.config.Configuration;
import org.grizz.session.Session;
import org.grizz.session.SessionProvider;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.grizwold.microblog.model.Entry;
import pl.grizwold.microblog.model.serializer.DateTimeSerializer;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Slf4j
@Service
public class EntryProvider {
    private final ModelResolver modelResolver = new ModelResolver();
    private final Gson gson = new GsonBuilder()
            .registerTypeAdapter(DateTime.class, new DateTimeSerializer())
            .create();

    @Autowired
    private SessionProvider sessionProvider;

    public Set<Entry> getEntries(Configuration configuration) {
        Session session = sessionProvider.getGetterSession();
        DateTime entriesMaxAge = DateTime.now().minusHours(configuration.getHoursOfHistory());

        return IntStream.range(0, 100)
                .parallel()
                .mapToObj(page -> getPage(page, session, configuration.getWykopEndpoint()))
                .flatMap(Collection::stream)
                .filter(entry -> isOldEntry(entry, entriesMaxAge))
                .collect(Collectors.toSet());
    }

    private List<Entry> getPage(int page, Session session, Configuration.WykopEndpoint endpoint) {
        Command command = getCommand(endpoint.getResource(), endpoint.getMethod(), String.valueOf(page));
        String jsonResult = session.execute(command);
        return Arrays.asList(gson.fromJson(jsonResult, modelResolver.resolve(endpoint.getResource())));
    }

    private boolean isOldEntry(Entry e, DateTime entriesMaxAge) {
        return e.getDateAdded().isAfter(entriesMaxAge);
    }

    private Command getCommand(String resource, String method, String... arguments) {
        Command command = new Command(resource, method, arguments);
        command.setClear(true);
        return command;
    }
}
