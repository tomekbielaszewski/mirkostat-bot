package org.grizz.service.post;


import org.apache.log4j.Logger;

/**
 * Created by Grizz on 2014-06-22.
 */
public class SystemOutEntryPoster implements EntryPoster {
    private final Logger logger = Logger.getLogger(this.getClass());

    @Override
    public void post(String mirkoStatBotEntryBody) {
        logger.info(mirkoStatBotEntryBody);
    }
}
