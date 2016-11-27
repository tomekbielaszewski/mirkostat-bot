package org.grizz.springconfig;

import org.grizz.service.post.EntryPoster;
import org.grizz.service.post.SystemOutEntryPoster;
import org.grizz.service.provider.EntryProvider;
import org.grizz.service.provider.WykopocztaEntryProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/**
 * Created by Grizz on 2014-07-03.
 */
@Profile("wykopoczta")
@Configuration
public class WykopocztaConfig {
    public static final String USER = "zly_wieczor";

    @Bean
    public EntryPoster entryPoster() {
        return new SystemOutEntryPoster();
    }

    @Bean
    public EntryProvider entryProvider() {
        return new WykopocztaEntryProvider();
    }
}
