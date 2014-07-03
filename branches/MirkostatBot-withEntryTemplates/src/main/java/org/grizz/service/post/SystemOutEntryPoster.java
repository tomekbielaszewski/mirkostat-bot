package org.grizz.service.post;

/**
 * Created by Grizz on 2014-06-22.
 */
public class SystemOutEntryPoster implements EntryPoster {
    @Override
    public void post(String mirkoStatBotEntryBody) {
        System.out.println(mirkoStatBotEntryBody);
    }
}
