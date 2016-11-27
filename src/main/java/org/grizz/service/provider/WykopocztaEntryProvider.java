package org.grizz.service.provider;

import com.crozin.wykop.sdk.Command;
import com.crozin.wykop.sdk.Session;
import org.grizz.model.Entry;
import org.grizz.service.EntryFactory;
import org.grizz.springconfig.WykopocztaConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Grizz on 2014-12-05.
 */
@Service
public class WykopocztaEntryProvider implements EntryProvider {

    @Autowired
    private Session session;
    @Autowired
    private EntryFactory entryFactory;

    @Override
    public List<Entry> getEntries() {
        List<Entry> entries = new ArrayList<>();

        for (int i = 0; i < 3; i++) {
            Command com = new Command("profile", "entries", new String[]{WykopocztaConfig.USER});
            com.addParameter("page", String.valueOf(i));
            String json = session.execute(com);
            entries.addAll(Arrays.asList(entryFactory.getEntries(json)));
        }

        return entries;
    }
}
