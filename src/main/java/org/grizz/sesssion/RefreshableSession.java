package org.grizz.sesssion;

import com.crozin.wykop.sdk.AuthenticatedSession;
import com.crozin.wykop.sdk.Command;
import com.crozin.wykop.sdk.exception.ConnectionException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Slf4j
@Service
public class RefreshableSession {
    private int maxAttempts;

    @Autowired
    private SessionProvider sessionProvider;

    private AuthenticatedSession session;

    @PostConstruct
    public void init() {
        maxAttempts = sessionProvider.getKeysAmount();
    }

    public void startNew() {
        session = sessionProvider.getSession();
    }

    public String execute(Command cmd) {
        if (session == null) startNew();
        String result = null;

        for (int i = 0; i < maxAttempts; i++) {
            try {
                result = session.execute(cmd);
                return result;
            } catch (ConnectionException e) {
                if (i >= maxAttempts) {
                    log.error("All API keys exhausted!");
                    throw e;
                }
                log.warn("Current API key exhausted - using next one");
                startNew();
            }
        }

        return result;
    }
}
