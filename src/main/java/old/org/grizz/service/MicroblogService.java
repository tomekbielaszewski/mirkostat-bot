package old.org.grizz.service;

import com.crozin.wykop.sdk.AuthenticatedSession;
import com.crozin.wykop.sdk.Command;
import com.crozin.wykop.sdk.Session;
import org.apache.log4j.Logger;
import old.org.grizz.model.Entry;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.SimpleDateFormat;

public class MicroblogService {
    private final Logger logger = Logger.getLogger(this.getClass());
    private final String accountKey;

    @Autowired
    private Session session;
    @Autowired
    private EntryFactory entryFactory;

    public MicroblogService(String accountKey) {
        this.accountKey = accountKey;
    }

    public Entry[] index(int page) {
        logger.info("Page: " + page);

        String json = session.execute(getMikroblogLatestCommand(page));
        Entry[] entries = entryFactory.getEntries(json);

        logger.info(" | Last entry date: " + new SimpleDateFormat("d k:m:s").format(entries[entries.length - 1].getDateAdded()));

        return entries;
    }

    public Entry[] next(Long entryId) {
        StringBuilder sb = new StringBuilder();
        sb.append("First id: " + entryId);

        String json = session.execute(getMikroblogAfterCommand(entryId));
        Entry[] entries = entryFactory.getEntries(json);

        sb.append(" | Last id: " + entries[entries.length - 1].getId());
        sb.append(" | Last entry date: " + new SimpleDateFormat("dd kk:mm:ss").format(entries[entries.length - 1].getDateAdded()));
        logger.info(sb.toString());

        return entries;
    }

    public Integer add(String entryBody) {
        AuthenticatedSession authenticatedSession = (AuthenticatedSession) session;

        return authenticatedSession.getMicroblogService().addEntry(entryBody);
    }

    private Command getMikroblogLatestCommand(Integer page) {
        return new Command("stream", "index", new String[]{"page", page.toString()});
    }

    private Command getMikroblogAfterCommand(Long id) {
        return new Command("stream", "firstid", new String[]{id.toString()});
    }
}
