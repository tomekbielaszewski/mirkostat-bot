package org.grizz.service.post;


import org.grizz.service.MicroblogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Grizz on 2014-06-22.
 */
@Service("mirkoEntryPoster")
public class MirkoEntryPoster implements EntryPoster {
    @Autowired
    private MicroblogService microblogService;

    @Override
    public void post(String mirkoStatBotEntryBody) {
        System.out.println("Posting new entry...");

        String result = microblogService.add(mirkoStatBotEntryBody);

        System.out.println("Result:\n" + result);
    }
}
