package org.grizz.service;

import lombok.extern.slf4j.Slf4j;
import org.grizz.session.ClientProvider;
import org.springframework.stereotype.Service;
import pl.grizwold.wykop.WykopClient;
import pl.grizwold.wykop.model.WykopResponse;
import pl.grizwold.wykop.resources.entries.EntryAdd;

@Slf4j
@Service
public class EntryPoster {
    private final ClientProvider clientProvider;

    public EntryPoster(ClientProvider clientProvider) {
        this.clientProvider = clientProvider;
    }

    public void post(String formattedStats) {
        WykopClient client = clientProvider.getWriterSession();
        EntryAdd entryAdd = EntryAdd.builder().body(formattedStats).build();
        WykopResponse response = entryAdd.call(client);
        log.info(response.getJson());
    }

}
