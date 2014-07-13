package org.grizz.springconfig;

import org.grizz.service.post.EntryPoster;
import org.grizz.service.post.SystemOutEntryPoster;
import org.grizz.service.provider.DevEntryProvider;
import org.grizz.service.provider.EntryProvider;
import org.grizz.service.provider.MirkoEntryProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/**
 * Created by Grizz on 2014-07-03.
 */
@Profile("dev")
@Configuration
public class DevConfig {
    private static final long COLLECTION_PERIOD = 86400000; //doba

    @Bean
    public EntryPoster entryPoster() {
        return new SystemOutEntryPoster();
    }

    @Bean
    public EntryProvider entryProvider() {
        return new DevEntryProvider();
//        return new MirkoEntryProvider(COLLECTION_PERIOD);
    }
}
