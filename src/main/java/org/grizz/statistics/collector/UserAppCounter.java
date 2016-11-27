package org.grizz.statistics.collector;

import org.grizz.model.Entry;
import org.grizz.model.EntryComment;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * Created by Grizz on 2014-07-04.
 */
@Service
public class UserAppCounter extends AbstractStatCounter {
    @Override
    public void collect(Entry entry) {
        String app = fixAppName(entry.getApp());

        addToCounter(app, 1);

        for (EntryComment entryComment : entry.getComments()) {
            app = fixAppName(entryComment.getApp());

            addToCounter(app, 1);
        }
    }

    public int getNumberOfApps() {
        return getCounter().size();
    }

    public String getAppOnPosition(int position) {
        return getEntryOnPosition(position).getKey().toString();
    }

    public int getAppCountOnPosition(int position) {
        return getEntryOnPosition(position).getValue();
    }

    public String[] getUserApps() {
        Map<Object, Integer> statCounter = getCounter();
        String[] userApps = new String[statCounter.size()*2];

        int counter = 0;

        for (Map.Entry<Object, Integer> entry : statCounter.entrySet()) {
            userApps[counter++] = entry.getKey().toString();
            userApps[counter++] = entry.getValue().toString();
        }

        return userApps;
    }

    private String fixAppName(String appName) {
        return appName == null ? "Wykop.pl" : appName;
    }
}
