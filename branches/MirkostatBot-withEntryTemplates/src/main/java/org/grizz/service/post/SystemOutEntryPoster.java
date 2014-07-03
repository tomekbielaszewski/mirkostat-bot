package org.grizz.service.post;

import org.springframework.stereotype.Service;

/**
 * Created by Grizz on 2014-06-22.
 */
@Service("systemOutEntryPoster")
public class SystemOutEntryPoster implements EntryPoster {
    @Override
    public void post(String mirkoStatBotEntryBody) {
        System.out.println(mirkoStatBotEntryBody);
    }
}
