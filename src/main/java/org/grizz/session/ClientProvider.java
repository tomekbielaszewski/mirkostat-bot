package org.grizz.session;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import pl.grizwold.wykop.WykopClient;
import pl.grizwold.wykop.model.ApiParam;

@Slf4j
@Service
public class ClientProvider {
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

    public WykopClient getReaderSession() {
        return new WykopClient(getterKeyPublic, getterKeySecret)
                .set(ApiParam.DATA_FULL)
                .set(ApiParam.OUTPUT_CLEAR);
    }

    public WykopClient getWriterSession() {
        return new WykopClient(posterKeyPublic, posterKeySecret, posterKeyUser);
    }
}
