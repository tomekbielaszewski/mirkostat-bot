package org.grizz.springconfig;

import org.grizz.service.post.EntryPoster;
import org.grizz.service.post.SystemOutEntryPoster;
import org.grizz.service.provider.DevEntryProvider;
import org.grizz.service.provider.EntryProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/**
 * Created by Grizz on 2014-07-03.
 */
@Profile("dev")
@Configuration
public class DevConfig {
    @Bean
    public EntryPoster entryPoster() {
        return new SystemOutEntryPoster();
    }

    @Bean
    public EntryProvider entryProvider() {
        return new DevEntryProvider();
    }
}
