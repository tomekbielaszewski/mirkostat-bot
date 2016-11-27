package old.org.grizz.service.post;


import old.org.grizz.service.MicroblogService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

/**
 * Created by Grizz on 2014-06-22.
 */
public class MirkoEntryPoster implements EntryPoster {
    private final Logger logger = Logger.getLogger(this.getClass());

    @Autowired
    private MicroblogService microblogService;

    @Override
    public void post(String mirkoStatBotEntryBody) {
        logger.info("Posting new entry...");
        logger.info(new Date().toString());

        Integer entryId = microblogService.add(mirkoStatBotEntryBody);

        logger.info("Entry ID: " + entryId);
    }
}
