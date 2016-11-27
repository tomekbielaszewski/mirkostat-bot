package org.grizz.springconfig;

import org.grizz.service.post.EntryPoster;
import org.grizz.service.post.MirkoEntryPoster;
import org.grizz.service.provider.EntryProvider;
import org.grizz.service.provider.MirkoEntryProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Profile("!dev")
@Configuration
public class AppConfig {
    private static final long COLLECTION_PERIOD = 86400000; //doba

    @Bean
    public EntryPoster entryPoster() {
        return new MirkoEntryPoster();
    }

    @Bean
    public EntryProvider entryProvider() {
        return new MirkoEntryProvider(COLLECTION_PERIOD);
    }
}
