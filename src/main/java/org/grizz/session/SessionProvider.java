package org.grizz.session;

import com.crozin.wykop.sdk.Application;
import lombok.extern.slf4j.Slf4j;
import org.grizz.model.WykopKeys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class SessionProvider {
    @Autowired
    private WykopKeys wykopKeys;

    public Session getPosterSession() {
        return new SessionTimeoutAdapter(new Application(wykopKeys.getPublicc(), wykopKeys.getSecret())
                .openSession(wykopKeys.getUser()));
    }
}
