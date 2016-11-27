package org.grizz.sesssion;

import com.crozin.wykop.sdk.Application;
import com.crozin.wykop.sdk.AuthenticatedSession;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Iterator;
import java.util.List;

@Slf4j
@Service
public class SessionProvider {
    private final static String PUBLIC_KEY = "mirko.key.public.";
    private final static String PRIVATE_KEY = "mirko.key.secret.";
    private Iterator<KeyPair> keysIterator;
    private int keysAmount;

    @Autowired
    private Environment env;

    @PostConstruct
    private void init() {
        List<KeyPair> keysList = Lists.newArrayList();
        String publicKey;
        String privateKey;
        String userKey;
        int counter = 1;

        log.info("Loading API keys...");
        while ((publicKey = env.getProperty(PUBLIC_KEY + counter)) != null &&
                (privateKey = env.getProperty(PRIVATE_KEY + counter)) != null) {
            keysList.add(new KeyPair(publicKey, privateKey));
            keysAmount = counter;
            counter++;
        }
        keysIterator = Iterables.cycle(keysList).iterator();

        log.info("Loaded {} API keys", counter - 1);
    }

    public synchronized AuthenticatedSession getSession() {
        KeyPair keys = keysIterator.next();
        Application app = new Application(keys.getPublicKey(), keys.getPrivateKey());
        return app.openSession(keys.getUserKey());
    }

    public int getKeysAmount() {
        return keysAmount;
    }

    @Getter
    @AllArgsConstructor
    private class KeyPair {
        private final String publicKey;
        private final String privateKey;
    }
}
