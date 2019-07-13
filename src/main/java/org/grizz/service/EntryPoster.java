package org.grizz.service;

import com.crozin.wykop.sdk.Command;
import lombok.extern.slf4j.Slf4j;
import org.grizz.session.Session;
import org.grizz.session.SessionProvider;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class EntryPoster {
    private final SessionProvider sessionProvider;

    public EntryPoster(SessionProvider sessionProvider) {
        this.sessionProvider = sessionProvider;
    }

    public void post(String formattedStats) {
        Session session = sessionProvider.getPosterSession();
        String result = session.execute(postCommand(formattedStats));
        log.info(result);
    }

    private Command postCommand(String bodyContent) {
        Command cmd = new Command("entries", "add");
        cmd.addPostParameter("body", bodyContent);
        return cmd;
    }
}
