package org.grizz.session;

import com.crozin.wykop.sdk.Application;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class SessionProvider {
    @Value("${getter.key.public}")
    private String getterKeyPublic;
    @Value("${getter.key.secret}")
    private String getterKeySecret;

    @Value("${poster.key.public}")
    private String posterKeyPublic;
    @Value("${poster.key.secret}")
    private String posterKeySecret;
    @Value("${poster.key.user}")
    private String posterKeyUser;

    public Session getGetterSession() {
        return new SessionTimeoutAdapter(new Application(getterKeyPublic, getterKeySecret).openSession());
    }

    public Session getPosterSession() {
        return new SessionTimeoutAdapter(new Application(posterKeyPublic, posterKeySecret).openSession(posterKeyUser));
    }
}
