package org.grizz.session;

import com.crozin.wykop.sdk.Command;
import com.crozin.wykop.sdk.Session;

public class SessionTimeoutAdapter implements org.grizz.session.Session {
    private final Session session;

    SessionTimeoutAdapter(Session session) {
        this.session = session;
    }

    public String execute(Command command) {
        //TODO implement timeout
        return session.execute(command);
    }
}
